package com.example.hospitalreservation.model.policies;

import com.example.hospitalreservation.model.FeePolicy;
import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class GeneralFeePolicy implements FeePolicy {
    private static final long GENERAL_FEE = 10000;

    @Override
    public boolean supports(String purpose) {
        return purpose.equals("일반 검진");
    }

    @Override
    public long calculateFee(Reservation reservation) {
        return GENERAL_FEE;
    }
}