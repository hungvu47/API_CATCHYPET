package com.hungvu.webgym.service;

import com.hungvu.webgym.model.Appointment;
import com.hungvu.webgym.request.AppointmentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAppointmentService {

    Appointment addAppointment(AppointmentRequest request);

    Appointment updateAppointment(Long appointmentId, AppointmentRequest request);

    void deleteAppointment(Long appointmentId);

    List<Appointment> getAllAppointment();

}
