package com.example.hospitalreservation.model.policies;

import com.example.hospitalreservation.model.FeePolicy;
import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ColdFeePolicy implements FeePolicy {
    private static final long COLD_FEE = 15000;

    @Override
    public boolean supports(String purpose) {
        return purpose.equals("감기 증상");
    }

    @Override
    public long calculateFee(Reservation reservation) {
        return COLD_FEE;
    }
}
