package com.astella.api;

import com.astella.api.model.Client;
import com.astella.api.model.ServiceDelivery;
import com.astella.api.service.ServiceDeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceDeliveryService serviceDelivery;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void testGetService() throws Exception {
        mockMvc.perform(get("/service/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetServices() throws Exception {
        mockMvc.perform(get("/services"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteServices() throws Exception {
        mockMvc.perform(delete("/delete"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteService() throws Exception {
        mockMvc.perform(delete("/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateService() throws Exception {
        ServiceDelivery service = ServiceDelivery.builder()
                .description("service description")
                .name("Manicure")
                .time(LocalTime.parse("01:00"))
                .build();

        Mockito.when(serviceDelivery.saveService(service)).thenReturn(service);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/service")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(service));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Manicure")));

    }


}