package scrumbledore.kinoproject.project.showing.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import scrumbledore.kinoproject.project.admindetails.AdminDetails;
import scrumbledore.kinoproject.project.film.entity.Film;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.theater.enity.Theater;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class Showing extends AdminDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Film film;
    @OneToMany(mappedBy = "showing")
    private List<Seat> seats;
    @ManyToOne
    private Theater theater;
    private LocalDateTime timeAndDate;
    private Double ticketPrice;
    @OneToMany
    private List<Reservation> reservations;



}
