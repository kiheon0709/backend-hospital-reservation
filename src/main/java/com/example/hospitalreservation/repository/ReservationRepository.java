package com.example.hospitalreservation.repository;

import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // 기본적인 CRUD 메서드는 자동 제공
    // findAll(), save(), deleteById(), findById()

    List<Reservation> findByDoctor_Id(Long doctorId);
}
