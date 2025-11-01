package com.SpringLearn.example.hospitalManagement.service;

import com.SpringLearn.example.hospitalManagement.entitiy.Patient;
import com.SpringLearn.example.hospitalManagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(){
        Patient p1 = patientRepository.findById(2L).orElseThrow();
        Patient p2 = patientRepository.findById(2L).orElseThrow();
        p1.setName("yoyo");
        return p1;
    }

}
