package com.astella.api;

import com.astella.api.model.Appointment;
import com.astella.api.service.AppointmentService;
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

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentDelivery;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void testGetAppointment() throws Exception {
        mockMvc.perform(get("/appointment/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAppointments() throws Exception {
        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAppointments() throws Exception {
        mockMvc.perform(delete("/deleteAppointments"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAppointment() throws Exception {
        mockMvc.perform(delete("/deleteAppointment/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAppointment() throws Exception {
        Appointment appointment = Appointment.builder()
                .note("appointment note")
                .date(LocalDate.parse("2023-01-22"))
                .time(LocalTime.parse("01:00"))
                .build();

        Mockito.when(appointmentDelivery.saveAppointment(appointment)).thenReturn(appointment);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(appointment));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.note", is("appointment note")));    }


}