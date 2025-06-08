package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.Patient;
import com.example.hospitalreservation.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // 해당 전화번호의 환자 조회, 존재하지 않으면 Optional.empty() 리턴
    public Optional<Patient> findByPhoneNumber(String phoneNumber) {
        return patientRepository.findByPhoneNumber(phoneNumber);
    }

    // 모든 환자 리스트 가져오기
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    // 새로운 환자 정보 생성
    public Patient createPatient(String name, String phoneNumber) {
        return patientRepository.save(new Patient(name, phoneNumber));
    }

    // 환자 조회 후 신규환자이면 환자 정보 저장
    public Patient findOrCreatePatient(String name, String phoneNumber) {
        return findByPhoneNumber(phoneNumber)
                .orElseGet(() -> createPatient(name, phoneNumber));
    }
}
