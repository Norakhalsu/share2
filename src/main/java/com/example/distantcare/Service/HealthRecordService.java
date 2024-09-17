package com.example.distantcare.Service;


import com.example.distantcare.Api.ApiException;
import com.example.distantcare.Model.Doctor;
import com.example.distantcare.Model.HealthRecord;
import com.example.distantcare.Model.Patient;
import com.example.distantcare.Model.User;
import com.example.distantcare.Repository.DoctorRepository;
import com.example.distantcare.Repository.HealthRecordRepository;
import com.example.distantcare.Repository.PatientRepository;
import com.example.distantcare.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthRecordService {
    private final HealthRecordRepository healthRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


    // ADMIN
    public void addHealthRecord(Integer patientId, HealthRecord healthRecord) {
        Patient patient = patientRepository.findPatientByPatientId(patientId);
        if (patient == null) {
            throw new ApiException("Patient not found");
        }
        healthRecord.setPatient(patient);
        healthRecordRepository.save(healthRecord);

    }


    // ADMIN
    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordRepository.findAll();
    }

    // ADMIN
    public void updateHealthRecord(Integer patientId,HealthRecord healthRecord) {
        Patient patient=patientRepository.findPatientByPatientId(patientId);
       // HealthRecord healthRecord1=healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
        if(patient==null) {
            throw new ApiException("Patient Not Found");
        }
        healthRecord.setHealthHabits(patient.getHealthRecord().getHealthHabits());
        healthRecord.setPastSurgeries(patient.getHealthRecord().getPastSurgeries());
        healthRecord.setPastMedications(patient.getHealthRecord().getPastMedications());
        healthRecord.setPatient(patient);
        healthRecordRepository.save(healthRecord);
    }

    // ADMIN
    public void deleteHealthRecord(Integer patientId,Integer healthRecordId) {
        Patient patient=patientRepository.findPatientByPatientId(patientId);
        HealthRecord healthRecord=healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
        if(patient==null) {
            throw new ApiException("Patient Not Found");
        }
        if(healthRecord==null) {
            throw new ApiException("Health Record Not Found");
        }
        healthRecordRepository.delete(healthRecord);
    }



// ------------------------------- end point ---------


    //Patient
    public HealthRecord getHealthRecordByPatientId(Integer patientId,Integer healthRecordId) {
        Patient patient = patientRepository.findPatientByPatientId(patientId);
        HealthRecord healthRecord = healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
        if (healthRecord == null) {
            throw new ApiException("Health Record Not Found");
        }
        if (healthRecord.getPatient().getPatientId().equals(patient.getPatientId())) {
            return healthRecord;
        } else {
            throw new ApiException("Patient Not Found");
        }

    }

public void patientHabitsUpdate(Integer patientId,  List<String> newHealthHabits) {
    HealthRecord healthRecord = healthRecordRepository.findHealthRecordByHealthRecordId(patientId);
    if(healthRecord==null){
        throw new ApiException("not found");
    }
    healthRecord.setHealthHabits(newHealthHabits);
    healthRecordRepository.save(healthRecord);
}



        public void addNewPastIllness(Integer healthRecordId, List<String> illness) {
        HealthRecord healthRecord = healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
                   if(healthRecord==null){
                       throw new ApiException("Health Record Not Found");
                   }
        healthRecord.setPastIllnesses(illness);
        healthRecordRepository.save(healthRecord);
    }


    public void addNewPastMedication(Integer healthRecordId, List<String> medication) {
        HealthRecord healthRecord = healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
        if (healthRecord == null) {
            throw new ApiException("HealthRecord not found");
        }
        healthRecord.setPastMedications(medication);
        healthRecordRepository.save(healthRecord);
    }



    public void addNewPastSurgery(Integer healthRecordId, List<String> surgery) {
        HealthRecord healthRecord = healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
        if (healthRecord == null) {
            throw new ApiException("HealthRecord not found");
        }
        healthRecord.setPastSurgeries(surgery);
        healthRecordRepository.save(healthRecord);
    }


    public void addNewHealthHabit(Integer healthRecordId, List<String> habit) {
        HealthRecord healthRecord = healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
        if (healthRecord == null) {
            throw new ApiException("HealthRecord not found");
        }
        healthRecord.setHealthHabits(habit);
        healthRecordRepository.save(healthRecord);
    }

    public void searchAndUpdatePastIllness(Integer healthRecordId, List<String> illnesses) {
        HealthRecord healthRecord = healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
        if (healthRecord == null) {
            throw new ApiException("HealthRecord not found");
        }

        List<String> currentIllnesses = healthRecord.getPastIllnesses();

        if (currentIllnesses.equals(illnesses)) {
            return;
        }
        healthRecord.setPastIllnesses(illnesses);
        healthRecordRepository.save(healthRecord);
    }



    public void updatePastMedicationsForHealthRecord(Integer patientId,  Integer healthRecordId,  List<String> pastMedications) {
        // Logic to update past medications for health record
    }











//    public HealthRecord getHealthRecordByPatientId( Integer patientId,  Integer healthRecordId) {
//        return healthRecordService.getHealthRecordByPatientId(patientId, healthRecordId);
//    }


}

    









