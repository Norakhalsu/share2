package com.example.distantcare.Service;


import com.example.distantcare.Api.ApiException;
import com.example.distantcare.DTO.DoctorDTO;
import com.example.distantcare.DTO.PatientDTO;
import com.example.distantcare.Model.*;
import com.example.distantcare.Repository.DoctorRepository;
import com.example.distantcare.Repository.HospitalRepository;
import com.example.distantcare.Repository.PatientRepository;
import com.example.distantcare.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;


    // ADMIN
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }


    // ALL
    public void addDoctor(DoctorDTO doctorDTO){
        // 2 object [ user , Doctor]
        User user = new User();
        user.setUsername(doctorDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(doctorDTO.getPassword()));
        user.setEmail(doctorDTO.getEmail());
        user.setRole("DOCTOR");
        user.setFullName(doctorDTO.getFullName());
        user.setAge(doctorDTO.getAge());
        user.setAddress(doctorDTO.getAddress());
        user.setBirthDate(doctorDTO.getBirthDate());
        user.setEmergencyPhoneNumber(doctorDTO.getEmergencyPhoneNumber());
        user.setGender(doctorDTO.getGender());
        user.setCity(doctorDTO.getCity());


        Doctor doctor=new Doctor();
        doctor.setMajor(doctorDTO.getMajor());
        doctor.setHospitalName(doctorDTO.getHospitalName());
        doctor.setDegree(doctorDTO.getDegree());

        doctor.setUser(user);
        userRepository.save(user);
        doctorRepository.save(doctor);
    }


    // ADMIN --------------
    public void updateDoctor(Integer id,Doctor doctor) {
        Doctor doctor1 =doctorRepository.findDoctorByDoctorId(id);
        if(doctor1==null){
            throw new ApiException("not found");
        }
        //  patient1.setHealthStatus(patient.getHealthStatus());
        //   patient1.setMedicalHistory(patient.getMedicalHistory());
        doctorRepository.save(doctor1);
    }


    // ADMIN ----------
    public void deleteDoctor(Integer id) {
        Doctor doctor =doctorRepository.findDoctorByDoctorId(id);
        if(doctor==null){
            throw new ApiException("not found");
        }
        doctorRepository.delete(doctor);
    }

   // ------------------------- End point -------------------------------

    // DOCTOR ---
   public int doctorAppointmentsCount(Integer doctorId) {
      Doctor doctor = doctorRepository.findDoctorByDoctorId(doctorId);

       if (doctor==null) {
           throw new ApiException("Doctor with ID " + doctorId + " not found");
       }
           Set<Appointment> appointments = doctor.getAppointments();
           return appointments.size();
          }




          // get all patient with doctor
    public List<Patient> getPatientsWithAppointments(Integer doctorId) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);

        if (!doctor.isPresent()) {
            throw new ApiException("Doctor with ID " + doctorId + " not found");
        }
            Doctor doctor11 = doctor.get();
            Set<Appointment> appointments = doctor11.getAppointments();
            List<Patient> patientNames = new ArrayList<>();

            for (Appointment appointment : appointments) {
                patientNames.add(appointment.getPatient());
            }
            return patientNames;
        }


      // HOSPITAL
    public Doctor getDoctorById(Integer hospitalId, Integer doctorId) {
        Hospital hospital=hospitalRepository.findHospitalByHospitalId(hospitalId);
        if(hospital==null){
            throw new ApiException("Hospital with ID " + hospitalId + " not found");
        }
        Doctor doctor = doctorRepository.findDoctorByDoctorId(doctorId);
        if(doctor==null){
            throw new ApiException("Doctor with ID " + doctorId + " not found");
        }
        if(hospital.getDoctors().contains(doctor)){
            return doctor;
        }
        throw new ApiException("Doctor with ID " + doctorId + " not found");
    }


//    public void addRatingToDoctor( Integer patientId,Integer doctorId,  int rating) {
//        Doctor doctor = doctorRepository.findDoctorByDoctorId(doctorId);
//        if(doctor==null){
//            throw new ApiException("Doctor with ID " + doctorId + " not found");
//        }
//        Patient patient=patientRepository.findPatientByPatientId(patientId);
//        if (patient==null) {
//            throw new ApiException("Patient with ID " + patientId + " not found");
//        }
//        Requests requests=new Requests();
//        if(requests.getHospital().getDoctors())
//        doctor.setRatingToPatient(rating);
//        doctorRepository.save(doctor);
//    }
//
//
//    @GetMapping("/doctors/search")
//    public List<Doctor> searchDoctorsByMajor(@RequestParam String major) {
//        return doctorRepository.findByMajor(major);
//    }
//
//    public void deleteRatingFromDoctor( Integer id) {
//        Doctor doctor = doctorRepository.findDoctorByDoctorId(id);
//        doctor.setRatingToPatient(0);
//        doctorRepository.save(doctor);
//    }
//
//
//    @PostMapping("/doctors/{id}/appointments")
//    public void addAppointmentToDoctor(@PathVariable Integer id, @RequestBody Appointment appointment) {
//        Doctor doctor = doctorRepository.findDoctorByDoctorId(id);
//        // Add appointment logic here
//    }
//
//
//
//
//    @PutMapping("/doctors/{id}/degree")
//    public void updateDoctorDegree(@PathVariable Integer id, @RequestParam String degree) {
//        Doctor doctor = doctorRepository.findDoctorByDoctorId(id);
//        doctor.setDegree(degree);
//        doctorRepository.save(doctor);
//    }
//
//
//    @GetMapping("/doctors/sorted")
//    public List<Doctor> getDoctorsSortedByRating() {
//        return doctorRepository.findAll(Sort.by(Sort.Direction.DESC, "ratingToPatient"));
//    }
//






















          // تفاصيل الموعيد بين الدكنور والمريض
//        public  Appointment getAppointmentWithPatient(Integer doctorId, Integer appointmentId) {}
//    Appointment appointment = doctorService.getPatientAppointment(doctorId, patientId);
//
//    if(appointment != null) {
//        return ResponseEntity.status(200).body(appointment);
//    }



}
