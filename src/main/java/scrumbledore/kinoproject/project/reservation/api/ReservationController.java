package scrumbledore.kinoproject.project.reservation.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import scrumbledore.kinoproject.project.reservation.dto.ReservationRequestAddById;
import scrumbledore.kinoproject.project.reservation.dto.ReservationResponse;
import scrumbledore.kinoproject.project.reservation.service.ReservationService;
import scrumbledore.kinoproject.project.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    ReservationService reservationService;
    UserService userService;

    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @GetMapping()
    public List<ReservationResponse> findReservationsByCustomerUsername() {
        return reservationService.findReservationsByCustomerUsername();

    }

    @PostMapping()
    public int addReservation(@RequestBody ReservationRequestAddById body) {
        return reservationService.addReservation(body);
    }

    @GetMapping("/id/{id}")
    public ReservationResponse getReservationById(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }
}
