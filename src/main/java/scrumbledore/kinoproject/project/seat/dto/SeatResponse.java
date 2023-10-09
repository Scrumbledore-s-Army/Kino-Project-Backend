package scrumbledore.kinoproject.project.seat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.showing.entity.Showing;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatResponse {

    private int id;
    private Integer seatNumber;
    private Reservation reservation;
    private int showingIdOnShowing;

    public SeatResponse(Seat seat) {
        this.id = seat.getId();
        this.seatNumber = seat.getSeatNumber();
        this.reservation = seat.getReservation();
        this.showingIdOnShowing = seat.getShowingIdOnShowing();
    }
}
