package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.dto.CancelRequestDto;
import com.example.hospitalreservation.dto.ReservationRequestDto;
import com.example.hospitalreservation.dto.ReservationResponseDto;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import com.example.hospitalreservation.service.FeeCalculator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


// TODO : 컨트롤러에 필요한 어노테이션을 작성해주세요.
// TODO : 요청 경로는 templates를 참고하여 작성해주세요.

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    // TODO : 주입 받아야 할 객체를 설정해주세요.
    private final ReservationService reservationService;
    private final FeeCalculator feeCalculator;
    public ReservationController(ReservationService reservationService, FeeCalculator feeCalculator) {
        this.reservationService = reservationService;
        this.feeCalculator = feeCalculator;
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getReservations() {
        // TODO : 예약 메인 페이지를 가져오는 코드를 작성해주세요.
        List<Reservation> reservations = reservationService.getAllReservations();

        List<ReservationResponseDto> responseDtos = reservations.stream()
                .map(reservation -> {
                    return ReservationResponseDto.fromReservation(reservation, feeCalculator.calculateFee(reservation));
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(
            @RequestBody ReservationRequestDto requestDto) {
        // TODO : 예약을 진행하는 코드를 작성해주세요.
        Reservation reservation = reservationService.createReservation(requestDto.toReservation(null));
        return ResponseEntity.ok(ReservationResponseDto.fromReservation(reservation));
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelReservation(
            @PathVariable Long id,
            @RequestBody CancelRequestDto cancelRequestDto) {
        // TODO : 예약을 취소하는 코드를 작성해주세요.
        reservationService.cancelReservation(id, cancelRequestDto.getCancelReason());
        return ResponseEntity.ok("예약이 성공적으로 취소되었습니다. (ID: " + id + ") 취소 사유 : \"" + cancelRequestDto.getCancelReason() + "\"");
    }
}