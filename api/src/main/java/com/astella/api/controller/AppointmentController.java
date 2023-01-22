package com.astella.api.controller;


import com.astella.api.model.Appointment;
import com.astella.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/appointment")
    public Appointment createAppointment(@RequestBody Appointment appointment){
        return appointmentService.saveAppointment(appointment);
    }

    @GetMapping("/appointment/{id}")
    public Appointment getById(@PathVariable("id") final Long id)
    {
        return appointmentService.getAppointment(id).orElse(null);
    }

    @GetMapping("/appointments")
    public Iterable<Appointment> getAll(){
        return appointmentService.getAppointments();
    }

    @DeleteMapping("/deleteAppointment/{id}")
    public void delete(@PathVariable("id") final Long id){
        appointmentService.deleteAppointment(id);
    }

    @DeleteMapping("/deleteAppointments")
    public void deleteAll(){
        appointmentService.deleteAppointments();
    }

    @PutMapping("/appointment/{id}")
    public Appointment updateAppointment(@PathVariable("id") final Long id, @RequestBody Appointment appointmentUpdate){
        Optional<Appointment> appointment = appointmentService.getAppointment(id);

        if (appointment.isPresent()){
            Appointment currentAppointment = appointment.get();
            if (appointmentUpdate.getDate() != null) {
                currentAppointment.setDate(appointmentUpdate.getDate());
            }
            if (appointmentUpdate.getTime() != null) {
                currentAppointment.setTime(appointmentUpdate.getTime());
            }
            if (appointmentUpdate.getClient() != null) {
                currentAppointment.setClient(appointmentUpdate.getClient());
            }
            if (appointmentUpdate.getNote() != null) {
                currentAppointment.setNote(appointmentUpdate.getNote());
            }

            return appointmentService.saveAppointment(currentAppointment);
        }

        else return null;

    }

}
