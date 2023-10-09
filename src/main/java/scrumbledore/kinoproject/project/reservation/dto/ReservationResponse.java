package scrumbledore.kinoproject.project.reservation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.showing.entity.Showing;
import scrumbledore.kinoproject.project.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {
    private List<Seat> seats = new ArrayList<>();
    private Integer totalPrice;
    private User customer;
    private Showing showing;
    public ReservationResponse(ReservationRequest reservationRequest) {
        this.seats = reservationRequest.getSeats();
        this.totalPrice = reservationRequest.getTotalPrice();
        this.customer = reservationRequest.getCustomer();
        this.showing = reservationRequest.getShowing();
    }
}
