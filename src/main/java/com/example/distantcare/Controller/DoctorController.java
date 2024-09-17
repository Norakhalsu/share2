package com.example.distantcare.Controller;


import com.example.distantcare.Api.ApiResponse;
import com.example.distantcare.DTO.DoctorDTO;
import com.example.distantcare.Model.Doctor;
import com.example.distantcare.Model.User;
import com.example.distantcare.Service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/registration")// ALL
    public ResponseEntity creatDoctor(@RequestBody @Valid DoctorDTO doctorDTO){
        doctorService.addDoctor(doctorDTO);
        return new ResponseEntity("Doctor registered successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-all")// ADMIN -- has Authority
    public ResponseEntity getDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }


    @PutMapping("/update/{doctorId}")// Doctor تحديث البيانات الشخصيه
    public ResponseEntity updatePatient(@AuthenticationPrincipal User doctorUser, @RequestBody @Valid Doctor doctor){
        doctorService.updateDoctor(doctorUser.getId(),doctor);
        return new ResponseEntity("Doctor updated successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{doctorId}") //ADMIN
    public ResponseEntity deletePatient(@PathVariable Integer doctorId){
        doctorService.deleteDoctor(doctorId);
        return new ResponseEntity("Doctor deleted successfully", HttpStatus.OK);
    }
   // --------------------------- End Point ---------------

    // Endpoint للحصول على عدد المواعيد للطبيب بناءً على doctorId
    @GetMapping("/{doctorId}/appointments/count")
    public ResponseEntity<Integer> getDoctorAppointmentsCount(@PathVariable Integer doctorId) {
        int count = doctorService.doctorAppointmentsCount(doctorId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/all-patient/{doctorId}")
    public ResponseEntity getPatientAppointments(@PathVariable Integer doctorId) {
        return ResponseEntity.status(200).body(doctorService.getPatientsWithAppointments(doctorId));
    }

}
