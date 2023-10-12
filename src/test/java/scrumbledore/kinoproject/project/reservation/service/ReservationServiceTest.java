package scrumbledore.kinoproject.project.reservation.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;
import scrumbledore.kinoproject.project.reservation.dto.ReservationRequestAddById;
import scrumbledore.kinoproject.project.reservation.entity.Reservation;
import scrumbledore.kinoproject.project.reservation.repository.ReservationRepository;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.seat.repository.SeatRepository;
import scrumbledore.kinoproject.project.showing.entity.Showing;
import scrumbledore.kinoproject.project.showing.repository.ShowingRepository;
import scrumbledore.kinoproject.project.user.entity.User;
import scrumbledore.kinoproject.project.user.repository.UserRepository;
import scrumbledore.kinoproject.security.TestUtils;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private SeatRepository seatRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ShowingRepository showingRepository;

    @BeforeAll
    public static void iniSetUp() {
        System.setProperty(TestUtils.h2UrlName, TestUtils.h2UrlValue);
        System.setProperty(TestUtils.h2UsernameName, TestUtils.h2UsernameValue);
        System.setProperty(TestUtils.h2PassName, TestUtils.h2PassValue);
    }

    @AfterAll
    public static void tearDown() {
        System.clearProperty(TestUtils.h2UrlName);
        System.clearProperty(TestUtils.h2UsernameName);
        System.clearProperty(TestUtils.h2PassName);
    }

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(reservationRepository, seatRepository, userRepository, showingRepository);
    }

    @Test
    void addReservationShouldAddReservation() {
        ReservationRequestAddById reservationRequest = new ReservationRequestAddById();
        reservationRequest.setUsername("testUser");
        reservationRequest.setShowingId(1);
        reservationRequest.setSeatIds(new Integer[]{1, 2, 3});

        User user = new User("testUser", "password", "test@example.com");
        Seat seat1 = new Seat();
        Seat seat2 = new Seat();
        Seat seat3 = new Seat();
        Showing showing = new Showing();

        when(userRepository.findById("testUser")).thenReturn(Optional.of(user));
        when(showingRepository.findById(1)).thenReturn(Optional.of(showing));
        when(seatRepository.findById(1)).thenReturn(Optional.of(seat1));
        when(seatRepository.findById(2)).thenReturn(Optional.of(seat2));
        when(seatRepository.findById(3)).thenReturn(Optional.of(seat3));

        when(seatRepository.saveAll(anyList())).thenAnswer(invocation -> {
            List<Seat> seats = invocation.getArgument(0);
            for (Seat seat : seats) {
                int i = seats.indexOf(seat);
                seat.setId(i);
            }
            return seats;
        });

        ArgumentCaptor<List<Seat>> seatCaptor = ArgumentCaptor.forClass(List.class);

        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> {
            Reservation reservation = invocation.getArgument(0);
            reservation.setId(1);
            return reservation;
        });

        reservationService.addReservation(reservationRequest);

        verify(seatRepository).saveAll(seatCaptor.capture());

        List<Seat> savedSeats = seatCaptor.getValue();
        assertEquals(3, savedSeats.size());
    }

    @Test
    void addReservationShouldThrowResponseStatusException() {
        ReservationRequestAddById reservationRequest = new ReservationRequestAddById();
        reservationRequest.setUsername("nonExistentUser");
        reservationRequest.setShowingId(1);
        reservationRequest.setSeatIds(new Integer[]{1, 2, 3});

        when(userRepository.findById("nonExistentUser")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            reservationService.addReservation(reservationRequest);
        });
    }

    @Test
    void addReservationThrowResponseStatusException() {
        ReservationRequestAddById reservationRequest = new ReservationRequestAddById();
        reservationRequest.setUsername("testUser");
        reservationRequest.setShowingId(1);
        reservationRequest.setSeatIds(new Integer[]{1, 999});

        User user = new User("testUser", "password", "test@example.com");
        Seat seat1 = new Seat();
        Showing showing = new Showing();

        when(userRepository.findById("testUser")).thenReturn(Optional.of(user));
        when(showingRepository.findById(1)).thenReturn(Optional.of(showing));
        when(seatRepository.findById(1)).thenReturn(Optional.of(seat1));
        when(seatRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            reservationService.addReservation(reservationRequest);
        });
    }

   @Test
    void findReservationsByCustomerUsername() {
        String username = "testUser";
        User user = new User("testUser", "password", "test@test.dk");

        Seat seat1 = new Seat();
        Seat seat2 = new Seat();
        Seat seat3 = new Seat();
        Showing showing = new Showing();
        Reservation reservation = new Reservation();
        reservation.setCustomer(user);
        reservation.setShowing(showing);
        reservation.setId(1);

        ReservationRequestAddById reservationRequest = new ReservationRequestAddById();
        reservationRequest.setUsername("testUser");
        reservationRequest.setShowingId(1);
        reservationRequest.setSeatIds(new Integer[]{1, 2, 3});

        when(userRepository.findById("testUser")).thenReturn(Optional.of(user));
        when(showingRepository.findById(1)).thenReturn(Optional.of(showing));
        when(seatRepository.findById(1)).thenReturn(Optional.of(seat1));
        when(seatRepository.findById(2)).thenReturn(Optional.of(seat2));
        when(seatRepository.findById(3)).thenReturn(Optional.of(seat3));

        when(seatRepository.saveAll(anyList())).thenAnswer(invocation -> {
            List<Seat> seats = invocation.getArgument(0);
            for (Seat seat : seats) {
                int i = seats.indexOf(seat);
                seat.setId(i);
            }
            return seats;
        });

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        reservationService.addReservation(reservationRequest);

        ArgumentCaptor<List<Seat>> seatCaptor = ArgumentCaptor.forClass(List.class);
        verify(seatRepository).saveAll(seatCaptor.capture());

        List<Seat> savedSeats = seatCaptor.getValue();
        assertEquals(3, savedSeats.size());
    }

}
