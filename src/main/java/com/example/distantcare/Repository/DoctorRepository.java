package com.example.distantcare.Repository;


import com.example.distantcare.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findDoctorByDoctorId(Integer doctorId);
}
