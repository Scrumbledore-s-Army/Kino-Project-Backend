package scrumbledore.kinoproject.project.reservation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.showing.entity.Showing;
import scrumbledore.kinoproject.project.user.entity.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    private List<Seat> seats;
    private Integer totalPrice;
    @ManyToOne
    private User customer;
    @ManyToOne
    private Showing showing;
}
