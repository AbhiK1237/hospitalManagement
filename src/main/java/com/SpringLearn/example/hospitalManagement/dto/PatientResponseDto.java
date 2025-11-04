package com.SpringLearn.example.hospitalManagement.dto;

import com.SpringLearn.example.hospitalManagement.Enum.BloodGroupType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroupType;
}
