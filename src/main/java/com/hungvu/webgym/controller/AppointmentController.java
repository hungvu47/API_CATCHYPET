package com.hungvu.webgym.controller;

import com.hungvu.webgym.model.Appointment;
import com.hungvu.webgym.request.AppointmentRequest;
import com.hungvu.webgym.service.IAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService bookingService;

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllAppointment());
    }

    @PostMapping("/create")
    public ResponseEntity<?> addBooking(@RequestBody AppointmentRequest appointmentRequest) {
        try {
            bookingService.addAppointment(appointmentRequest);
            return new ResponseEntity<>("Appointment created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{appointmentId}")
    public ResponseEntity<?> updateBooking (@RequestBody AppointmentRequest appointmentRequest,
                                                 @PathVariable Long appointmentId) {
        Appointment updateAppointment = bookingService.updateAppointment(appointmentId, appointmentRequest);
        return ResponseEntity.ok(updateAppointment);
    }

    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long appointmentId) {
        bookingService.deleteAppointment(appointmentId);
        return ResponseEntity.ok("xóa thành công");
    }
}
