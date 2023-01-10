package com.astella.api;

import com.astella.api.model.Client;
import com.astella.api.service.ClientService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void testGetClient() throws Exception {
        mockMvc.perform(get("/client/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClients() throws Exception {
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteClients() throws Exception {
        mockMvc.perform(delete("/delete"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteClient() throws Exception {
        mockMvc.perform(delete("/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateClient() throws Exception {
        mockMvc.perform(post("/client"))
                .andExpect(status().isOk());
    }

    @Test
    public void createRecord_success() throws Exception {
        Client client = Client.builder()
                .name("John Doe")
                .phonenumber("0123456789")
                .email("jd@mail.fr")
                .build();

        Mockito.when(clientService.saveClient(client)).thenReturn(client);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(client));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("John Doe")));
    }

}