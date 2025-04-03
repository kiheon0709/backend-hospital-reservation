package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Reservation;

import java.time.LocalDateTime;

public class ReservationRequestDto {
    private final Long doctorId;
    private final Long patientId;
    private final LocalDateTime reservationTime;

    // 생성자
    public ReservationRequestDto(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationTime = reservationTime;
    }

    // 정적 팩토리 메서드
    public static ReservationRequestDto fromReservation(Reservation reservation) {
        return new ReservationRequestDto(
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getReservationTime()
        );
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;

    }
}
