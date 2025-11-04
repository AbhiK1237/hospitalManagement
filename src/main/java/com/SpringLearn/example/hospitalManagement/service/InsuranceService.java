package com.SpringLearn.example.hospitalManagement.service;

import com.SpringLearn.example.hospitalManagement.entitiy.Insurance;
import com.SpringLearn.example.hospitalManagement.entitiy.Patient;
import com.SpringLearn.example.hospitalManagement.repository.InsuranceRepository;
import com.SpringLearn.example.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;



    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance,Long patientid){
        Patient patient = patientRepository.findById(patientid)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id" + patientid));

        patient.setInsurance(insurance);
        insurance.setPatient(patient); // maintains  bidirectional consistency
        return patient;
    }

    @Transactional
    public Patient removeInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id" + patientId));
        patient.setInsurance(null);
        return patient;
    }
}
