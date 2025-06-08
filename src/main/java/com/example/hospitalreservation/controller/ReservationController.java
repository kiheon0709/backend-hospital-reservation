package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.dto.CancelRequestDto;
import com.example.hospitalreservation.dto.ReservationRequestDto;
import com.example.hospitalreservation.dto.ReservationResponseDto;
import com.example.hospitalreservation.model.Patient;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.PatientService;
import com.example.hospitalreservation.service.ReservationService;
import com.example.hospitalreservation.service.FeeCalculator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final PatientService patientService;
    private final FeeCalculator feeCalculator;

    public ReservationController(ReservationService reservationService,
                                 PatientService patientService,
                                 FeeCalculator feeCalculator) {
        this.reservationService = reservationService;
        this.patientService = patientService;
        this.feeCalculator = feeCalculator;
    }

    // 예약 목록 조회 - 진료비 포함
    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();

        List<ReservationResponseDto> responseDtos = reservations.stream()
                .map(reservation -> {
                    return ReservationResponseDto.fromReservation(reservation, feeCalculator.calculateFee(reservation));
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    // 예약 생성
    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(
            @RequestBody ReservationRequestDto requestDto) {
        // 1. 환자 생성 or 조회
        Patient patient = patientService.findOrCreatePatient(
                requestDto.getPatientName(),
                requestDto.getPatientPhoneNumber()
        );

        // 2. 예약 생성 및 저장 (의사 매칭 포함)
        Reservation reservation = reservationService.createReservation(
                requestDto
        );

        long fee = feeCalculator.calculateFee(reservation);
        return ResponseEntity.ok(ReservationResponseDto.fromReservation(reservation, fee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelReservation(
            @PathVariable Long id,
            @RequestBody CancelRequestDto cancelRequestDto) {
        reservationService.cancelReservation(id, cancelRequestDto.getCancelReason());
        return ResponseEntity.ok("예약이 성공적으로 취소되었습니다. (ID: " + id + ") 취소 사유 : \"" + cancelRequestDto.getCancelReason() + "\"");
    }
}