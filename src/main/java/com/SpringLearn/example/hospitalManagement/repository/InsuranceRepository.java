package com.SpringLearn.example.hospitalManagement.repository;

import com.SpringLearn.example.hospitalManagement.entitiy.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}