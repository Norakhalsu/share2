package com.example.distantcare.Service;


import com.example.distantcare.Api.ApiException;
import com.example.distantcare.DTO.HospitalDTO;
import com.example.distantcare.Model.Doctor;
import com.example.distantcare.Model.Hospital;
import com.example.distantcare.Model.Requests;
import com.example.distantcare.Model.User;
import com.example.distantcare.Repository.DoctorRepository;
import com.example.distantcare.Repository.HospitalRepository;
import com.example.distantcare.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public void addHospitalToSystem(HospitalDTO hospitalDTO) {
        User user = new User();
        user.setUsername(hospitalDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(hospitalDTO.getPassword()));
        user.setEmail(hospitalDTO.getEmail());
        user.setRole("HOSPITAL");
        user.setAddress(hospitalDTO.getAddress());
        user.setEmergencyPhoneNumber(hospitalDTO.getEmergencyPhoneNumber());
        user.setCity(hospitalDTO.getCity());


        Hospital hos=new Hospital();
        hos.setHospitalName(hospitalDTO.getHospitalName());

        hos.setUser(user);
        userRepository.save(user);
        hospitalRepository.save(hos);
    }


    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }



    // ADMIN
    public void updateHospital(Integer hospitalId,Hospital hospital) {
        Hospital hospitalToUpdate = hospitalRepository.findHospitalByHospitalId(hospitalId);
        if (hospitalToUpdate == null) {
            throw new ApiException("Hospital not found");
        }
        hospitalToUpdate.setHospitalName(hospital.getHospitalName());
        hospitalToUpdate.setUser(hospital.getUser());
        hospitalRepository.save(hospital);
    }


    // ADMIN
    public void deleteHospital(Integer hospitalId) {
        Hospital hospital=hospitalRepository.findHospitalByHospitalId(hospitalId);
        if (hospital == null) {
            throw new ApiException("Hospital not found");
        }
        hospitalRepository.delete(hospital);
    }
   // -------------------------------- End point -----------------


    // HOSPITAL
    public int patientsCount(Integer hospitalId) {
        Hospital hospital = hospitalRepository.findHospitalByHospitalId(hospitalId);
        if (hospital == null) {
            throw new ApiException("Hospital not found");
        }
        int patientCount = hospitalRepository.getPatientCountByHospitalId(hospitalId);
        if (patientCount == 0) {
            throw new ApiException("No patients found");
        }
        hospital.setPatientCount(patientCount);
        hospitalRepository.save(hospital);
        return patientCount;
    }



        public void removeDoctorFromHospital(Integer hospitalId, Integer doctorId) {
        Hospital hospital = hospitalRepository.findHospitalByHospitalId(hospitalId);
        Doctor doctor = doctorRepository.findDoctorByDoctorId(doctorId);

        if (hospital == null) {
            throw new ApiException("Hospital not found");
        }
        if (doctor == null) {
            throw new ApiException("Doctor not found");
        }
        if (!hospital.getDoctors().contains(doctor)) {
            throw new ApiException("Doctor not found in Hospital");
        }

        hospital.getDoctors().remove(doctor);
        doctor.setHospitalName(null); // تعيين المستشفى الخاص بالطبيب إلى قيمة null
        doctorRepository.save(doctor); // حفظ التغييرات على الطبيب
    }


        public List<Requests> getRequestSetByHospital(Integer hospitalId) {
        Hospital hospital = hospitalRepository.findHospitalByHospitalId(hospitalId);
        if (hospital == null) {
            throw new ApiException("Hospital not found");
        }
        if (hospital.getRequestSet().size() == 0) {
            throw new ApiException("No set requests found");
        }
        return new ArrayList<>(hospital.getRequestSet());
         }




         

}
