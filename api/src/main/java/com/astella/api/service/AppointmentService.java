package com.astella.api.service;

import com.astella.api.model.Appointment;
import com.astella.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment saveAppointment(Appointment appointment){return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getAppointment(Long id){
        return appointmentRepository.findById(id);
    }

    public Iterable<Appointment> getAppointments(){
        return appointmentRepository.findAll();
    }

    public void deleteAppointment(long id){
        appointmentRepository.deleteById(id);
    }

    public void deleteAppointments(){
        appointmentRepository. deleteAll();
    }

}
