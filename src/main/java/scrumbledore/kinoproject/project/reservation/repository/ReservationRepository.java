package scrumbledore.kinoproject.project.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scrumbledore.kinoproject.project.reservation.dto.ReservationResponse;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.user.dto.UserResponse;
import scrumbledore.kinoproject.project.user.entity.User;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<ReservationResponse> findReservationsByCustomer(User customer);


}
