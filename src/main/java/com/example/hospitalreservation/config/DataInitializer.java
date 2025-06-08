package com.example.hospitalreservation.config;

import com.example.hospitalreservation.model.ConsultationTime;
import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final DoctorRepository doctorRepository;

    @Override
    public void run(String... args) {
        doctorRepository.save(new Doctor("홍기헌", "내과",
                ConsultationTime.NINE_TO_FIVE.getStart(), ConsultationTime.NINE_TO_FIVE.getEnd()));
    }
}
