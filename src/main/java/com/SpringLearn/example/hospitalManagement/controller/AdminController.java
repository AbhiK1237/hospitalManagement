package com.SpringLearn.example.hospitalManagement.controller;

import com.SpringLearn.example.hospitalManagement.dto.PatientResponseDto;
import com.SpringLearn.example.hospitalManagement.service.PatientService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value="page", defaultValue = "0")Integer pageNumber,
            @RequestParam(value="size", defaultValue = "10")Integer pageSize
    ){
        return ResponseEntity.ok(patientService.getAllPatients(pageNumber,pageSize));
    }
}
