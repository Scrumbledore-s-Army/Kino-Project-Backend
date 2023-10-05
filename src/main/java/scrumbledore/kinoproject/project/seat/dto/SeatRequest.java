package scrumbledore.kinoproject.project.seat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.showing.entity.Showing;

@Getter
@Setter
@NoArgsConstructor
public class SeatRequest {
    private Showing showing;
    private Integer seatNumber;
    private Reservation reservation;
    public SeatRequest(Seat seat) {
        this.showing = seat.getShowing();
        this.seatNumber = seat.getSeatNumber();
        this.reservation = seat.getReservation();
    }
}
