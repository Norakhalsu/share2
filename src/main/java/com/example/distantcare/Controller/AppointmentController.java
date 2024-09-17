package com.example.distantcare.Controller;


import com.example.distantcare.Model.Appointment;
import com.example.distantcare.Model.Doctor;
import com.example.distantcare.Model.User;
import com.example.distantcare.Service.AppointmentService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/add-appointment/{doctorId}/{patientId}")// DOCTOR
    public ResponseEntity addAppointment(@PathVariable Integer doctorId, @PathVariable int patientId, @RequestBody @Valid Appointment appointment) {
        appointmentService.addAppointment(doctorId , patientId, appointment);
        return ResponseEntity.status(200).body("Appointment added successfully");
    }

    @GetMapping("/get-all")// DOCTOR has Authority
    public ResponseEntity getAllAppointments() {
        return ResponseEntity.status(200).body(appointmentService.getAllAppointment());
    }
       @PutMapping("/update/{appointmentId}")// PATIENT
        public ResponseEntity updateAppointment(@AuthenticationPrincipal User patientUser, @PathVariable int appointmentId, @RequestBody @Valid Appointment appointment) {
           appointmentService.updateAppointment(patientUser.getId(), appointmentId, appointment);
           return ResponseEntity.status(200).body("Appointment updated successfully");
       }
        @DeleteMapping("/delete/{appointmentId}")// PATIENT
           public ResponseEntity deleteAppointment(@AuthenticationPrincipal User patientUser, @PathVariable int appointmentId) {
            appointmentService.deleteAppointment(patientUser.getId(), appointmentId);
            return ResponseEntity.status(200).body("Appointment deleted successfully");
        }
        // ----------------------------- End point --------------------

    @GetMapping("/byDate/{date}")
    public List<Appointment> getAppointmentsByDate(@PathVariable Date date) {
        return appointmentService.getAllAppointmentByDate(date);
    }


    @PutMapping("/status-update/{appointmentId}")
    public ResponseEntity updateAppointmentStatus(@PathVariable Integer appointmentId, @RequestBody @Valid String newStatus , @RequestBody @Valid LocalDate newDate) {
        appointmentService.statusUpdateAppointment(appointmentId, newStatus, newDate);
        return ResponseEntity.status(200).body("Appointment Rescheduled successfully");
    }

    @PutMapping("/{appointmentId}/cancel")
    public ResponseEntity cancelAppointment(@PathVariable Integer appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.status(200).body("Appointment Cancelled successfully");
    }

//    @GetMapping("/byDoctor/{doctorName}")
//    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable String doctorName) {
//        List<Appointment> list=appointmentService.findAppointmentByDoctorName(doctorName);
//        return ResponseEntity.status(200).body(list);
//    }



}
