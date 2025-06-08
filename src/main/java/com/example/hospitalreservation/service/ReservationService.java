package com.example.hospitalreservation.service;

import com.example.hospitalreservation.dto.ReservationRequestDto;
import com.example.hospitalreservation.model.Doctor;
import com.example.hospitalreservation.model.MedicalPurposeGroup;
import com.example.hospitalreservation.model.Patient;
import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.DoctorRepository;
import com.example.hospitalreservation.repository.PatientRepository;
import com.example.hospitalreservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class ReservationService {

    private static final Logger log = LoggerFactory.getLogger(ReservationService.class);
    private final ReservationRepository reservationRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;


    public ReservationService(ReservationRepository reservationRepository,
                              DoctorService doctorService,
                              PatientService patientService) {
        this.reservationRepository = reservationRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    // 예약 리스트 조회
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // 새로운 예약 생성
    public Reservation createReservation(ReservationRequestDto requestDto) {
        // 1. 환자 생성 or 조회
        Patient patient = patientService.findOrCreatePatient(
                requestDto.getPatientName(),
                requestDto.getPatientPhoneNumber()
        );

        // 2. 의사 찾기
        Doctor doctor = doctorService.findAvailableDoctor(
                requestDto.getReason(),
                requestDto.getReservationStartTime(),
                requestDto.getReservationEndTime()
        );

        // 3. 예약 객체 생성
        Reservation reservation = new Reservation(
                doctor,
                patient,
                requestDto.getReservationStartTime(),
                requestDto.getReservationEndTime(),
                requestDto.getReason()
        );

        // 4. 유효성 검사 후 저장
        validateReservation(reservation);
        return reservationRepository.save(reservation);
    }

    // 예약 검증 메서드
    public void validateReservation(Reservation reservation) {
        LocalDateTime startTime = reservation.getReservationStartTime();
        LocalDateTime endTime = reservation.getReservationEndTime();
        Doctor doctor = reservation.getDoctor();  // 이미 배정된 의사

        // 1. 예약 시작시간은 현재시간 이후여야 함
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("현재 시간 이후로만 예약할 수 있습니다.");
        }

        // 2. 예약 종료시간은 현재시간보다 이후여야 함
        if (endTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("현재 시간 이후로만 예약할 수 있습니다.");
        }

        // 3. 예약 종료시간은 예약 시작시간 이후여야함
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("예약 종료시간은 예약 시작시간보다 늦어야 합니다.");
        }

        // 4. 의사의 진료시간 범위 내인지 확인
        if (!doctorService.isWithinConsultationTime(doctor, startTime.toLocalTime()) ||
                !doctorService.isWithinConsultationTime(doctor, endTime.toLocalTime())) {
            throw new IllegalArgumentException("의사의 진료 가능 시간(" +
                    doctor.getConsultationStartTime() + " ~ " + doctor.getConsultationEndTime() +
                    ") 내에서만 예약할 수 있습니다.");
        }

        // 4. 의사의 기존 예약과 겹치는지 확인
        List<Reservation> existingReservations = reservationRepository.findByDoctor_Id(doctor.getId());
        for (Reservation existing : existingReservations) {
            if (startTime.isBefore(existing.getReservationEndTime()) &&
                    endTime.isAfter(existing.getReservationStartTime())) {
                throw new IllegalArgumentException("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
            }
        }
    }

    // 예약 취소
    public void cancelReservation(Long id, String reason) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));

        log.info("예약 ID {} 취소됨. 사유: {}", id, reason);
        reservationRepository.deleteById(id);
    }
}
