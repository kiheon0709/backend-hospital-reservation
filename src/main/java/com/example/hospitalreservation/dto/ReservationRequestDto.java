package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ReservationRequestDto {
    private final Long doctorId;
    private final Long patientId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime reservationStartTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime reservationEndTime;

    @JsonProperty("reason")  // JSON 필드명 매핑
    private final String medicalPurpose;

    // 생성자
    public ReservationRequestDto(Long doctorId, Long patientId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime, String medicalPurpose) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.medicalPurpose = medicalPurpose;
    }

    // 정적 팩토리 메서드
    public Reservation toReservation(Long id) {
        return new Reservation(
                id,
                this.doctorId,
                this.patientId,
                this.reservationStartTime,
                this.reservationEndTime,
                this.medicalPurpose
        );
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
