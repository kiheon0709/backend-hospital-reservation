package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.MedicalPurposeGroup;
import com.example.hospitalreservation.repository.DoctorRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // 모든 의사 리스트 가져오기
    public List<Doctor> findAllDoctor() {
        return doctorRepository.findAll();
    }

    public Doctor findAvailableDoctor(
            String medicalPurpose,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        // 1. 진료 목적에 따른 전문분야 추출
        String specialization = MedicalPurposeGroup.getSpecializationByPurpose(medicalPurpose);

        // 2. 진료 시간 조건으로 가능한 의사 리스트 탐색
        List<Doctor> availableByTime = doctorRepository.findByAvailableTimeRange(
                startDateTime.toLocalTime(),
                endDateTime.toLocalTime()
        );

        // 3. 시간 조건 통과한 의사들 중에서 전문분야 일치하는 의사 찾기
        return availableByTime.stream()
                .filter(doc -> doc.getSpecialization().equals(specialization))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 시간에 진료 가능한 의사가 없습니다."));

    }

    // 진료시간 내 가능한지 판단
    public boolean isWithinConsultationTime(Doctor doctor, LocalTime time) {
        return !time.isBefore(doctor.getConsultationStartTime()) &&
                time.isBefore(doctor.getConsultationEndTime());
    }

}
