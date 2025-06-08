package com.example.hospitalreservation.model;

public interface FeePolicy {
    boolean supports(String purpose);
    long calculateFee(Reservation reservation);
}
