package scrumbledore.kinoproject.project.reservation.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scrumbledore.kinoproject.project.reservation.dto.ReservationResponse;
import scrumbledore.kinoproject.project.reservation.service.ReservationService;
import scrumbledore.kinoproject.project.user.dto.UserResponse;
import scrumbledore.kinoproject.project.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    ReservationService reservationService;
    UserService userService;

    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @GetMapping("/reservations/{username}")
    public List<ReservationResponse> getAllReservationsByUser(@PathVariable("username") String username) {
        UserResponse user = userService.findById(username);
        return reservationService.getAllReservationsByUser(user);
    }

}
