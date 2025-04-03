package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.dto.ReservationRequestDto;
import com.example.hospitalreservation.dto.ReservationResponseDto;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


// TODO : 컨트롤러에 필요한 어노테이션을 작성해주세요.
// TODO : 요청 경로는 templates를 참고하여 작성해주세요.

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    // TODO : 주입 받아야 할 객체를 설정해주세요.
    private ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @GetMapping("/reservations")
    public List<ReservationResponseDto> getReservations() {
        // TODO : 예약 메인 페이지를 가져오는 코드를 작성해주세요.
        List<Reservation> reservations = reservationService.getAllReservations();
        return reservations.stream()
                .map(ReservationResponseDto::fromReservation)
                .collect(Collectors.toList());
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @GetMapping("/new")
    public String showReservationForm() {
        // TODO : 예약하기 페이지를 가져오는 코드를 작성해주세요.
        return "reservation_form";
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationRequestDto requestDto) {
        // TODO : 예약을 진행하는 코드를 작성해주세요.
        Reservation reservation = reservationService.createReservation(
                requestDto.getDoctorId(),
                requestDto.getPatientId(),
                requestDto.getReservationTime()
        );
        ReservationResponseDto responseDto = ReservationResponseDto.fromReservation(reservation);
        return ResponseEntity.ok(responseDto);
    }

    // TODO : 필요한 어노테이션을 작성해주세요.
    @PostMapping("/delete/{id}")
    public String cancelReservation(
            @PathVariable Long id,
            @RequestParam String reason,
            RedirectAttributes redirectAttributes) {
        // TODO : 예약을 취소하는 코드를 작성해주세요.
        try {
            reservationService.cancelReservation(id, reason);
            redirectAttributes.addFlashAttribute("successMessage", "예약이 성공적으로 취소되었습니다. (ID: " + id + ")");
            return "redirect:/reservations";
        }  catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/reservations";
        }
    }
}