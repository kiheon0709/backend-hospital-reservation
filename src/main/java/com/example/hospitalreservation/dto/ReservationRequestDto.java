package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ReservationRequestDto {
    private final Long doctorId;
    private final Long patientId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime reservationStartTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime reservationEndTime;

    // 생성자
    public ReservationRequestDto(Long doctorId, Long patientId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
    }

    // 정적 팩토리 메서드
    public static ReservationRequestDto fromReservation(Reservation reservation) {
        return new ReservationRequestDto(
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getReservationStartTime(),
                reservation.getReservationEndTime()
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
}
