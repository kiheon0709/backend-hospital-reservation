package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Reservation;

import java.time.LocalDateTime;

public class ReservationResponseDto {
    private final Long id;
    private final Long doctorId;
    private final Long patientId;
    private final LocalDateTime reservationStartTime;
    private final LocalDateTime reservationEndTime;

    // 생성자
    public ReservationResponseDto(Long id, Long doctorId, Long patientId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
    }

    // 정적 팩토리 메서드
    public static ReservationResponseDto fromReservation(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getReservationStartTime(),
                reservation.getReservationEndTime()
        );
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
}
