package com.SpringLearn.example.hospitalManagement.service;

import com.SpringLearn.example.hospitalManagement.dto.AppointmentResponseDto;
import com.SpringLearn.example.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.SpringLearn.example.hospitalManagement.entitiy.Appointment;
import com.SpringLearn.example.hospitalManagement.entitiy.Doctor;
import com.SpringLearn.example.hospitalManagement.entitiy.Patient;
import com.SpringLearn.example.hospitalManagement.repository.AppointmentRepository;
import com.SpringLearn.example.hospitalManagement.repository.DoctorRepository;
import com.SpringLearn.example.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public AppointmentResponseDto createNewAppointement(CreateAppointmentRequestDto createAppointmentRequestDto){
        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new EntityNotFoundException("Doctor not found with ID: " + doctorId)
        );
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new EntityNotFoundException("Patient not found with ID: "+patientId)
        );


        Appointment appointment = Appointment.builder()
                .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
                .reason(createAppointmentRequestDto.getReason())
                .build();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.getAppointments().add(appointment);
        appointment =  appointmentRepository.save(appointment);
        return modelMapper.map(appointment,AppointmentResponseDto.class);
    }

    @Transactional
    public Appointment updateAppointment(Long appointmentId,Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);//this will automatically call the update,because its dirty

        doctor.getAppointments().add(appointment);
        return appointment;
    }

    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new EntityNotFoundException("doctor not found with id: "+doctorId)
        );

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment,AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }

}
