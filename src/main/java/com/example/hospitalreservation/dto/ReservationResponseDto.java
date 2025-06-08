package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ReservationResponseDto {
    private final Long id;
    private final Long doctorId;
    private final String doctorName;
    private final Long patientId;
    private final String patientName;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime reservationStartTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime reservationEndTime;

    @JsonProperty("reason")
    private final String medicalPurpose;

    private final Long fee;

    // 생성자
    public ReservationResponseDto(Long id,
                                  Long doctorId,
                                  String doctorName,
                                  Long patientId,
                                  String patientName,
                                  LocalDateTime reservationStartTime,
                                  LocalDateTime reservationEndTime,
                                  String medicalPurpose,
                                  Long fee) {
        this.id = id;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.patientName = patientName;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.medicalPurpose = medicalPurpose;
        this.fee = fee;
    }

    // 예약 객체 생성할 때는 진료비 계산 안하므로 fee없는 정팩메 메서드 오버로딩함.
    public static ReservationResponseDto fromReservation(Reservation reservation) {
        return fromReservation(reservation, 0); // 진료비는 아직 계산 전이므로 0으로 초기화
    }

    public static ReservationResponseDto fromReservation(Reservation reservation, long fee) {
        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getDoctor().getId(),
                reservation.getDoctor().getName(),
                reservation.getPatient().getId(),
                reservation.getPatient().getName(),
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

    public String getDoctorName() { return doctorName; }

    public Long getPatientId() {
        return patientId;
    }

    public String getPatientName() { return patientName; }

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
