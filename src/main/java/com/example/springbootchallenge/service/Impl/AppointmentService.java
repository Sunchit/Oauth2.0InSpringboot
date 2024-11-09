package com.example.springbootchallenge.service.Impl;

import com.example.springbootchallenge.repository.Appointment;
import com.example.springbootchallenge.repository.AppointmentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment saveAppointment(@Valid Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}