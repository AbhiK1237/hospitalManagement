package com.SpringLearn.example.hospitalManagement.controller;

import com.SpringLearn.example.hospitalManagement.dto.AppointmentResponseDto;
import com.SpringLearn.example.hospitalManagement.dto.DoctorResponseDto;
import com.SpringLearn.example.hospitalManagement.repository.DoctorRepository;
import com.SpringLearn.example.hospitalManagement.service.AppointmentService;
import com.SpringLearn.example.hospitalManagement.service.DoctorService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")

public class DoctorController {

        private final AppointmentService appointmentService;

        @GetMapping("/appointments")
        public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor(){
            return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(1L));
        }
}
