package com.example.hospitalreservation.model.policies;

import com.example.hospitalreservation.model.FeePolicy;
import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class RecoveryInjectionFeePolicy implements FeePolicy {
    private static final long RECOVERYINJECTION_FEE = 25000;

    @Override
    public boolean supports(String purpose) {
        return purpose.equals("피로 회복 주사");
    }

    @Override
    public long calculateFee(Reservation reservation) {
        return RECOVERYINJECTION_FEE;
    }
}
