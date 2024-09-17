package com.example.distantcare.Controller;


import com.example.distantcare.Api.ApiResponse;
import com.example.distantcare.DTO.PatientDTO;
import com.example.distantcare.Model.Patient;
import com.example.distantcare.Model.User;
import com.example.distantcare.Service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService PatientService;
    private final PatientService patientService;


    @GetMapping("/get-all")// ADMIN
    public ResponseEntity getPatient(){
        return ResponseEntity.ok(PatientService.getAllPatient());
    }

    @PostMapping("/registration/{hospitalId}")// ALL
    public ResponseEntity creatPatient(@PathVariable Integer hospitalId,@RequestBody @Valid PatientDTO patientDTO){
        PatientService.addPatientToHospital(hospitalId,patientDTO);
        return ResponseEntity.status(200).body("Patient Registered successfully");
    }

    @PutMapping("/update/{patientId}")// Patient تحديث البيانات الشخصيه
    public ResponseEntity updatePatient(@AuthenticationPrincipal User pateintUser,@RequestBody @Valid Patient patient){
        PatientService.updatePatient(pateintUser.getId(),patient);
        return ResponseEntity.status(200).body(new ApiResponse("Patient Data updated successfully"));
    }

    @DeleteMapping("/delete/{patientId}") //ADMIN
    public ResponseEntity deletePatient(@PathVariable Integer patientId){
        PatientService.deletePatient(patientId);
        return ResponseEntity.ok(new ApiResponse("Patient deleted successfully"));
    }

  // ----------------- End Point -------------------

    @GetMapping("all-patients-city/{city}")
    public ResponseEntity getAllPatientsByCity(@PathVariable String city){
        return ResponseEntity.status(200).body(patientService.getPatientsInCity(city));
    }



  }
