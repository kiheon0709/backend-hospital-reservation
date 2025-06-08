package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.ConsultationTime;
import com.example.hospitalreservation.model.Doctor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // 진료목적에 맞는 의사 탐색
//    List<Doctor> findByMedicalPurpose(String medicalPurpose);

    // 진료시간에 맞는 의사 탐색
    @Query("SELECT d FROM Doctor d WHERE d.consultationStartTime <= :startTime AND d.consultationEndTime >= :endTime")
    List<Doctor> findByAvailableTimeRange(@Param("startTime") LocalTime startTime,
                                          @Param("endTime") LocalTime endTime);

}