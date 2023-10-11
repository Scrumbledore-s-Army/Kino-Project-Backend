package scrumbledore.kinoproject.project.reservation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.showing.entity.Showing;
import scrumbledore.kinoproject.project.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    public ReservationResponse(Reservation reservation) {
        this.seats = reservation.getSeats();
        this.totalPrice = reservation.getTotalPrice();
    }
}
