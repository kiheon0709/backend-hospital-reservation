package com.example.hospitalreservation.model;

import java.util.Arrays;
import java.util.List;

public enum MedicalPurposeGroup {
    INTERNAL_MEDICINE(Arrays.asList("일반 검진", "감기 증상", "피로 회복 주사"), "내과");
//    FAMILY_MEDICINE(Arrays.asList("피로회복주사", "영양주사"), "가정의학과"),
//    ORTHOPEDICS(Arrays.asList("관절통증", "골절"), "정형외과");

    private final List<String> purposes;
    private final String specialization;

    MedicalPurposeGroup(List<String> purposes, String specialization) {
        this.purposes = purposes;
        this.specialization = specialization;
    }

    public static String getSpecializationByPurpose(String purpose) {
        for (MedicalPurposeGroup group : values()) {
            if (group.purposes.contains(purpose)) {
                return group.specialization;
            }
        }
        throw new IllegalArgumentException("해당 진료 목적에 대한 전문분야 매핑이 없습니다: " + purpose);
    }
}

