package com.SpringLearn.example.hospitalManagement.repository;

import com.SpringLearn.example.hospitalManagement.entitiy.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}