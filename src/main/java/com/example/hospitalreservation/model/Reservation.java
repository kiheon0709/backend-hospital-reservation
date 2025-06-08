package com.example.hospitalreservation.model;

import com.example.hospitalreservation.dto.ReservationResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "reservation_start_time", nullable = false)
    private LocalDateTime reservationStartTime;

    @Column(name = "reservation_end_time", nullable = false)
    private LocalDateTime reservationEndTime;

    @Column(name = "medical_purpose", nullable = false)
    private String medicalPurpose;

    protected Reservation() {}

    //
    public Reservation(Doctor doctor,
                       Patient patient,
                       LocalDateTime reservationStartTime,
                       LocalDateTime reservationEndTime,
                       String medicalPurpose) {
        this.doctor = doctor;
        this.patient = patient;
        this.reservationStartTime = reservationStartTime;
        this.reservationEndTime = reservationEndTime;
        this.medicalPurpose = medicalPurpose;
    }

    public Long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Long getDoctorId() {
        if (doctor != null) {
            return doctor.getId();
        } else {
            return null;
        }
    }

    public Patient getPatient() {
        return patient;
    }

    public Long getPatientId() {
        if (patient != null) {
            return patient.getId();
        } else {
            return null;
        }
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
