package com.SpringLearn.example.hospitalManagement.repository;

import com.SpringLearn.example.hospitalManagement.entitiy.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}