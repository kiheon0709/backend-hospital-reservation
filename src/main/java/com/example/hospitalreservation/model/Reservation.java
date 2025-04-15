package com.example.hospitalreservation.model;

import com.example.hospitalreservation.dto.ReservationResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Reservation {
    private final Long id;
    private final Long doctorId;
    private final Long patientId;
    private final LocalDateTime reservationStartTime;
    private final LocalDateTime reservationEndTime;
    private final String medicalPurpose;

    // TODO : 필요한 메서드가 있다면 작성해주세요.
    public Reservation(Long id, Long doctorId, Long patientId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime, String medicalPurpose) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.medicalPurpose = medicalPurpose;
    }

    public Long getId() {
        return id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public LocalDateTime getReservationStartTime() {
        return reservationStartTime;
    }

    public LocalDateTime getReservationEndTime() {
        return reservationEndTime;
    }

    public String getMedicalPurpose() {
        return medicalPurpose;
    }
}
