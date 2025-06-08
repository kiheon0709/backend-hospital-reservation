package com.example.hospitalreservation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    // 의사 진료 시간
    @Column(name = "consultationStartTime", nullable = false)
    private LocalTime consultationStartTime;

    @Column(name = "consultationEndTime", nullable = false)
    private LocalTime consultationEndTime;

    protected Doctor() { }

    // 생성자
    public Doctor(String name, String specialization,
                  LocalTime consultationStartTime, LocalTime consultationEndTime) {
        this.name = name;
        this.specialization = specialization;
        this.consultationStartTime = consultationStartTime;
        this.consultationEndTime = consultationEndTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() { return name; }

    public String getSpecialization() { return specialization; }

    public LocalTime getConsultationStartTime() {
        return consultationStartTime;
    }

    public LocalTime getConsultationEndTime() {
        return consultationEndTime;
    }

}