package scrumbledore.kinoproject.project.reservation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.showing.entity.Showing;
import scrumbledore.kinoproject.project.user.entity.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {
private List<Seat> seats;
private Integer totalPrice;
private User customer;
private Showing showing;

    public static Reservation getReservationEntity(ReservationRequest reservationRequest) {
        return new Reservation().builder()
                .seats(reservationRequest.getSeats())
                .totalPrice(reservationRequest.getTotalPrice())
                .customer(reservationRequest.getCustomer())
                .showing(reservationRequest.getShowing())
                .build();
    }

    public ReservationRequest(Reservation reservation) {
        this.seats = reservation.getSeats();
        this.totalPrice = reservation.getTotalPrice();
        this.customer = reservation.getCustomer();
        this.showing = reservation.getShowing();
    }
}
