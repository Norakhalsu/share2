package com.example.distantcare.Repository;

import com.example.distantcare.Model.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {
    HealthRecord findHealthRecordByHealthRecordId(Integer healthRecordId);
    //HealthRecord find
}
