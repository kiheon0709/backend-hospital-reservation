package com.example.hospitalreservation.dto;

import com.example.hospitalreservation.model.Reservation;

import java.time.LocalDateTime;

public class CancelRequestDto {
    private String cancelReason;

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
