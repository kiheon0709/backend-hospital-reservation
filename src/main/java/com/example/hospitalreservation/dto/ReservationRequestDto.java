package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.Patient;
import com.example.hospitalreservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationRequestDto {

    private String patientName;
    private String patientPhoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime reservationStartTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime reservationEndTime;

    private String reason; // 진료 목적

    // DTO → Patient 변환
    public Patient toPatient() {
        return new Patient(patientName, patientPhoneNumber);
    }

    // DTO → Reservation 변환 (의사 매칭은 서비스에서 처리하므로 doctorId X)
    public Reservation toReservation(Patient patient, Doctor doctor) {
        return new Reservation(
                doctor,
                patient,
                this.reservationStartTime,
                this.reservationEndTime,
                this.reason
        );
    }
}
