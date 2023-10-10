package scrumbledore.kinoproject.project.reservation.service;

import org.springframework.stereotype.Service;
import scrumbledore.kinoproject.project.reservation.dto.ReservationResponse;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.reservation.repository.ReservationRepository;
import scrumbledore.kinoproject.project.user.dto.UserResponse;

import java.util.List;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    public List<ReservationResponse> getAllReservationsByUser(UserResponse user) {
        return reservationRepository.findReservationsByCustomer(user);
    }

}
