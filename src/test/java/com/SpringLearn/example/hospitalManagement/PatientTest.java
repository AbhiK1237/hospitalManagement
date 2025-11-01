package com.SpringLearn.example.hospitalManagement;

import com.SpringLearn.example.hospitalManagement.Enum.BloodGroupType;
import com.SpringLearn.example.hospitalManagement.dto.BloodGroupCountResponseDto;
import com.SpringLearn.example.hospitalManagement.entitiy.Patient;
import com.SpringLearn.example.hospitalManagement.repository.PatientRepository;
import com.SpringLearn.example.hospitalManagement.service.PatientService;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){
        List<Patient> patients = patientRepository.findAllPatients();
        for(Patient p:patients) {
            System.out.println(p);
        }
    }

    @Test
    public void testTransactionMethods(){
//       Patient p = patientService.getPatientById();
//       System.out.println(p);

//        Patient patient = patientRepository.findByName("abhik");
//        System.out.println(patient);

//        List<Patient> patients = patientRepository.findByBirthdateOrEmail(LocalDate.of(1995,3,5),"teja@gmail.com");

//         List<Patient> patients = patientRepository.findByNameContaining("i");
//        List<Patient> patients = patientRepository.findByNameContainingOrderByIdDesc("i");
//        List<Patient> patients = patientRepository.findByBloodGroup(BloodGroupType.A_Positive);

//        List<Patient> patients = patientRepository.findByBornAfterDate(LocalDate.of(1995,3,5));

//         List<Object[]> objects = patientRepository.countBloodGrouptype();
//         for(Object[] object:objects){
//             System.out.println(object[0]+" "+object[1]);
//         }


//        List<BloodGroupCountResponseDto> bloodGroupCountResponse = patientRepository.countBloodGrouptype();
//        for(BloodGroupCountResponseDto bloodGroupCountResponseDto:bloodGroupCountResponse){
//             System.out.println(bloodGroupCountResponseDto);
//        }

        Page<Patient> patients = patientRepository.findAllPatients(PageRequest.of(0,2, Sort.by("name")));
        for (Patient p:patients) {
            System.out.println(p);
        }
//
//        int updatedCount = patientRepository.updateNameWithId("Abhijith",1L);
//        System.out.println(updatedCount);
    }
}
