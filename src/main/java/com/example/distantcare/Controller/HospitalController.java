package com.example.distantcare.Controller;

import com.example.distantcare.DTO.HospitalDTO;
import com.example.distantcare.Model.Hospital;
import com.example.distantcare.Model.User;
import com.example.distantcare.Service.HospitalService;
import com.example.distantcare.Service.HotLineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hospital")
public class HospitalController {
    private final HospitalService hospitalService;

    @PostMapping("/add")//ALL
    public ResponseEntity addHospital(@RequestBody @Valid HospitalDTO hospitalDTO) {
        hospitalService.addHospitalToSystem(hospitalDTO);
        return ResponseEntity.status(200).body("Hospital added successfully");
    }

    @GetMapping("/get-all")//ADMIN
    public ResponseEntity getAllHospitals() {
        return ResponseEntity.status(200).body(hospitalService.getAllHospitals());
    }

    @PutMapping("/update/{hospitalId}")//ADMIN
    public ResponseEntity updateHospital(@PathVariable  Integer hospitalId,@RequestBody @Valid Hospital hospital) {
        hospitalService.updateHospital(hospitalId,hospital);
        return ResponseEntity.status(200).body("hospital updated successfully");
    }

    @DeleteMapping("/delete/{hospitalId}")//ADMIN
    public ResponseEntity deleteHospital(@PathVariable  Integer hospitalId) {
        hospitalService.deleteHospital(hospitalId);
        return ResponseEntity.status(200).body("hospital deleted successfully");
    }

    // ----------------------------------- end point --------------

    // HOSPITAL
    @PutMapping("/patient-count/{hospitalId}")// patient count in hospital
    public ResponseEntity patientCount(@AuthenticationPrincipal User user) {
       return ResponseEntity.status(200).body(hospitalService.patientsCount(user.getId() ));
    }

    // HOSPITAL
   @PutMapping("/remove-doctor/{doctorId}")//
    public ResponseEntity removeDoctor(@AuthenticationPrincipal User user, @PathVariable  Integer doctorId) {
        hospitalService.removeDoctorFromHospital(user.getId(), doctorId);
        return ResponseEntity.status(200).body("doctor removed successfully");
   }

   // HOSPITAL
     @GetMapping("/hospital-requests")// all request of hospital
    public ResponseEntity getHospitalRequest(@AuthenticationPrincipal User user) {
       return ResponseEntity.status(200).body(hospitalService.getRequestSetByHospital(user.getId()));

     }




}
