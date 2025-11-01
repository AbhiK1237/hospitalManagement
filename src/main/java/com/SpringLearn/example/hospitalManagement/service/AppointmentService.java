package com.SpringLearn.example.hospitalManagement.service;

import com.SpringLearn.example.hospitalManagement.entitiy.Appointment;
import com.SpringLearn.example.hospitalManagement.entitiy.Doctor;
import com.SpringLearn.example.hospitalManagement.entitiy.Patient;
import com.SpringLearn.example.hospitalManagement.repository.AppointmentRepository;
import com.SpringLearn.example.hospitalManagement.repository.DoctorRepository;
import com.SpringLearn.example.hospitalManagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointement(Appointment appointment,Long doctorId,Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() !=null) throw new IllegalArgumentException("Appointment should not have present");
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.getAppointments().add(appointment);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment updateAppointment(Long appointmentId,Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);//this will automatically call the update,because its dirty

        doctor.getAppointments().add(appointment);
        return appointment;
    }



}
