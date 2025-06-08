package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.FeePolicy;
import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeeCalculator {
    private final List<FeePolicy> feePolicies;

    public FeeCalculator(List<FeePolicy> feePolicies) {
        this.feePolicies = feePolicies;
    }

    public final long calculateFee(Reservation reservation) {
        String purpose = reservation.getMedicalPurpose();

        FeePolicy selectedPolicy = feePolicies.stream()
                .filter(policy -> policy.supports(purpose))  // 해당 목적을 지원하는 정책 찾기
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 진료 목적입니다."));

        return selectedPolicy.calculateFee(reservation);
    }
}
