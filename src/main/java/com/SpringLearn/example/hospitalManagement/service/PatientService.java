package com.SpringLearn.example.hospitalManagement.service;

import com.SpringLearn.example.hospitalManagement.dto.PatientResponseDto;
import com.SpringLearn.example.hospitalManagement.entitiy.Patient;
import com.SpringLearn.example.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
//    @Transactional
//    public Patient getPatientById(){
//        Patient p1 = patientRepository.findById(2L).orElseThrow();
//        Patient p2 = patientRepository.findById(2L).orElseThrow();
//        p1.setName("yoyo");
//        return p1;
//    }

        public PatientResponseDto getPatientById(Long patientId){
            Patient patient =  patientRepository.findById(patientId).orElseThrow(()-> new EntityNotFoundException("patient not found with id"+patientId));
            return modelMapper.map(patient,PatientResponseDto.class);
        }

        public List<PatientResponseDto> getAllPatients(Integer pageNumber,Integer pageSize){
            return patientRepository.findAllPatients(PageRequest.of(pageNumber,pageSize))
                    .stream()
                    .map(patient -> modelMapper.map(patient,PatientResponseDto.class))
                    .collect(Collectors.toList());
        }

}
