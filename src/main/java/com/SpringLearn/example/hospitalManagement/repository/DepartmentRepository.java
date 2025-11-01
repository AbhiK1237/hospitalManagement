package com.SpringLearn.example.hospitalManagement.repository;

import com.SpringLearn.example.hospitalManagement.entitiy.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}