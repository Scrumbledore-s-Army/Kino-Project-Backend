package scrumbledore.kinoproject.project.showing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import scrumbledore.kinoproject.project.film.entity.Film;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.showing.entity.Showing;
import scrumbledore.kinoproject.project.theater.enity.Theater;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowingResponse {
    private int id;
    private Film film;
    private List<Seat> seats = new ArrayList<>();
    private Theater theater;
    private LocalDateTime timeAndDate;
    private Double ticketPrice;
    private List<Reservation> reservations = new ArrayList<>();

    public ShowingResponse(Showing showing) {
        this.id = showing.getId();
        this.film = showing.getFilm();
        this.theater = showing.getTheater();
        this.timeAndDate = showing.getTimeAndDate();
        this.ticketPrice = showing.getTicketPrice();
        this.reservations = showing.getReservations();
        this.seats = showing.getSeats();

        /*if (showing.getReservations() != null && !showing.getReservations().isEmpty()){
            this.reservations = showing.getReservations().stream()
                    .map(ReservationResponse::new).toList();
        }
        if(showing.getSeats() != null && !showing.getSeats().isEmpty()){
            this.seats = showing.getSeats().stream()
                    .map(SeatResponse::new).toList();
        }*/
    }
}
