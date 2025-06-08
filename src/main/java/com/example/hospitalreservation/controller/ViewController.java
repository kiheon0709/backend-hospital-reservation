package com.example.hospitalreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class ViewController {

    @GetMapping // HTML 페이지 반환
    public String showMainPage() {
        return "index";
    }

    @GetMapping("/new") // HTML 폼 페이지 반환
    public String showReservationForm() {
        return "reservation_form";
    }
}
