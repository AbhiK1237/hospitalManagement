package com.SpringLearn.example.hospitalManagement.repository;

import com.SpringLearn.example.hospitalManagement.Enum.BloodGroupType;
import com.SpringLearn.example.hospitalManagement.dto.BloodGroupCountResponseDto;
import com.SpringLearn.example.hospitalManagement.entitiy.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByName(String name);
    List<Patient> findByBirthdateOrEmail(LocalDate date, String email);
    List<Patient> findByNameContaining(String query);
    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("select p from Patient p where p.blood_group = ?1")
    List<Patient> findByBloodGroup(@Param("blood_group")BloodGroupType bloodGroup);

    @Query("select p from Patient p where p.birthdate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthdate);

    @Query("select new com.SpringLearn.example.hospitalManagement.dto.BloodGroupCountResponseDto(p.blood_group,Count(p)) from Patient p group by p.blood_group")
//  List<Object[]> countBloodGrouptype();
    List<BloodGroupCountResponseDto> countBloodGrouptype();


    @Query(value = "select * from Patient",nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Patient p set name = :name where id = :id")
    int updateNameWithId(@Param("name") String name,@Param("id") Long id);

    @Query("SELECT p from Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatients();
}
