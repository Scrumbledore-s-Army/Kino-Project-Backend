package scrumbledore.kinoproject.project.reservation.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import scrumbledore.kinoproject.project.reservation.dto.ReservationRequestAddById;
import scrumbledore.kinoproject.project.reservation.dto.ReservationResponse;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.reservation.repository.ReservationRepository;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.seat.repository.SeatRepository;
import scrumbledore.kinoproject.project.showing.repository.ShowingRepository;
import scrumbledore.kinoproject.project.user.entity.User;
import scrumbledore.kinoproject.project.user.repository.UserRepository;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    SeatRepository seatRepository;
    UserRepository userRepository;
    ShowingRepository showingRepository;

    public ReservationService(ReservationRepository reservationRepository, SeatRepository seatRepository, UserRepository userRepository, ShowingRepository showingRepository) {
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
        this.showingRepository = showingRepository;
    }

    public void addReservation(ReservationRequestAddById reservationRequest) {
        String username = reservationRequest.getUsername();
        int showingId = reservationRequest.getShowingId();
        Integer[] seatIds = reservationRequest.getSeatIds();

        Reservation reservation = new Reservation();
        reservation.setCustomer(userRepository.findById(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));

        ArrayList<Seat> seatsArrayList = new ArrayList<>();

   for (Integer seatId : seatIds) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found"));
            seatsArrayList.add(seat);
        }

        reservation.setSeats(seatsArrayList);
        reservation.setShowing(showingRepository.findById(showingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Showing not found")));

       reservation = reservationRepository.save(reservation);

       for (Seat seat : seatsArrayList){
           seat.setIsReserved(true);
       }

       seatRepository.saveAll(seatsArrayList);

    }

    public List<ReservationResponse> findReservationsByCustomerUsername(String username) {
        Optional<User> userOptional = userRepository.findById(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Reservation> reservations = reservationRepository.findReservationsByCustomer_Username(user.getUsername());
            List<ReservationResponse> reservationResponses = reservations.stream()
                    .map(ReservationResponse::new)
                    .toList();

            return reservationResponses;
        } else {
            return Collections.emptyList();
        }
    }
}
