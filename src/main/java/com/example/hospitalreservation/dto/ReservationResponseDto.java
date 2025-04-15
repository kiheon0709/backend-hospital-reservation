package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ReservationResponseDto {
    private final Long id;
    private final Long doctorId;
    private final Long patientId;
    private final LocalDateTime reservationStartTime;
    private final LocalDateTime reservationEndTime;
    private final String medicalPurpose;
    private final Long fee;

    // 생성자
    public ReservationResponseDto(Long id, Long doctorId, Long patientId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime, String medicalPurpose, Long fee) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.medicalPurpose = medicalPurpose;
        this.fee = fee;
    }

    // 예약 객체 생성할 때는 진료비 계산 안하므로 fee없는 정팩메 메서드 오버로딩함.
    public static ReservationResponseDto fromReservation(Reservation reservation) {
        return fromReservation(reservation, 0); // 진료비는 아직 계산 전이므로 0으로 초기화
    }

    // 정적 팩토리 메서드
    public static ReservationResponseDto fromReservation(Reservation reservation, long fee) {
        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getDoctorId(),
                reservation.getPatientId(),
                reservation.getReservationStartTime(),
                reservation.getReservationEndTime(),
                reservation.getMedicalPurpose(),
                fee
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

    public String getMedicalPurpose() {
        return medicalPurpose;
    }

    // getter 꼭 있어야 JS에서 ${reservation.fee} 로 접근 가능
    public long getFee() {
        return fee;
    }
}
