package com.SpringLearn.example.hospitalManagement.controller;

import com.SpringLearn.example.hospitalManagement.dto.AppointmentResponseDto;
import com.SpringLearn.example.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.SpringLearn.example.hospitalManagement.dto.PatientResponseDto;
import com.SpringLearn.example.hospitalManagement.service.AppointmentService;
import com.SpringLearn.example.hospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class PatientController {
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointement(createAppointmentRequestDto));
    }

    @GetMapping("/profile")
    public ResponseEntity<PatientResponseDto> getPatientProfile(){
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }
}
