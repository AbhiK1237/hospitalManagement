package com.SpringLearn.example.hospitalManagement.dto;

import com.SpringLearn.example.hospitalManagement.Enum.BloodGroupType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BloodGroupCountResponseDto {
    private BloodGroupType bloodGroupType;
    private Long count;
}
