package scrumbledore.kinoproject.project.showing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import scrumbledore.kinoproject.project.film.entity.Film;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.showing.entity.Showing;
import scrumbledore.kinoproject.project.theater.enity.Theater;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowingRequest {
    private int id;
    private Film film;
    private List<Seat> seats;
    private Theater theater;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timeAndDate;
    private Double ticketPrice;
    private List<Reservation> reservations;

    public static Showing getShowingEntity(ShowingRequest showingRequest){
        Showing showing = Showing.builder().ticketPrice(showingRequest.getTicketPrice())
                .reservations(showingRequest.getReservations())
                .timeAndDate(showingRequest.getTimeAndDate())
                .theater(showingRequest.getTheater())
                .film(showingRequest.getFilm())
                .seats(showingRequest.getSeats()).build();
        return showing;
    }

    public ShowingRequest(Showing showing) {
        this.film = showing.getFilm();
        this.seats = showing.getSeats();
        this.theater = showing.getTheater();
        this.timeAndDate = showing.getTimeAndDate();
        this.ticketPrice = showing.getTicketPrice();
        this.reservations = showing.getReservations();
    }
}
