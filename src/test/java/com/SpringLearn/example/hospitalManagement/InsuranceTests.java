package com.SpringLearn.example.hospitalManagement;

import com.SpringLearn.example.hospitalManagement.entitiy.Appointment;
import com.SpringLearn.example.hospitalManagement.entitiy.Insurance;
import com.SpringLearn.example.hospitalManagement.entitiy.Patient;
import com.SpringLearn.example.hospitalManagement.repository.InsuranceRepository;
import com.SpringLearn.example.hospitalManagement.service.AppointmentService;
import com.SpringLearn.example.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testInsurance(){
        Insurance insurance = Insurance.builder()
                .Provider("HDFC")
                .policyNumber("HDFC_1234")
                .validUntil(LocalDate.of(2026,6,4))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);

        var newPatient = insuranceService.removeInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }

    @Test
    public void testCreateAppointment(){
        Appointment appointment =  Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,10,31,14,0,0))
                .reason("fever")
                .build();

        var newAppointment = appointmentService.createNewAppointement(appointment,1L,2L);
        System.out.println(newAppointment);

        var updatedAppointment = appointmentService.updateAppointment(1L,2L);
        System.out.println(updatedAppointment);
    }
}

