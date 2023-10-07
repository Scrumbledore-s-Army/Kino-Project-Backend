package scrumbledore.kinoproject.project.showing.service;

import org.springframework.stereotype.Service;
import scrumbledore.kinoproject.project.film.entity.Film;
import scrumbledore.kinoproject.project.film.repository.FilmRepository;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.seat.repository.SeatRepository;
import scrumbledore.kinoproject.project.showing.dto.AddShowingRequest;
import scrumbledore.kinoproject.project.showing.dto.ShowingResponse;
import scrumbledore.kinoproject.project.showing.entity.Showing;
import scrumbledore.kinoproject.project.showing.repository.ShowingRepository;
import scrumbledore.kinoproject.project.theater.enity.Theater;
import scrumbledore.kinoproject.project.theater.repository.TheaterRepository;

@Service
public class ShowingService {

    FilmRepository filmRepository;
    TheaterRepository theaterRepository;

    SeatRepository seatRepository;

    ShowingRepository showingRepository;

    public ShowingService(FilmRepository filmRepository, TheaterRepository theaterRepository, ShowingRepository showingRepository, SeatRepository seatRepository) {
        this.filmRepository = filmRepository;
        this.theaterRepository = theaterRepository;
        this.showingRepository = showingRepository;
        this.seatRepository = seatRepository;
    }

    public ShowingResponse addShowing(AddShowingRequest addShowingRequest){
        Showing showing = new Showing();
        showing = showingRepository.save(showing);
        int showingId = showing.getId();

        int filmId = addShowingRequest.getFilmId();
        int theaterId = addShowingRequest.getTheaterId();

        Film film = filmRepository.findById(filmId).orElseThrow();
        showing.setFilm(film);

        Theater theater = theaterRepository.findById(theaterId).orElseThrow();
        showing.setTheater(theater);

        showing.setTicketPrice(addShowingRequest.getTicketPrice());
        showing.setTimeAndDate(addShowingRequest.getTimeAndDate());
        for (int i = 0; i < theater.getSeatCount(); i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i+1);

            seat.setShowingIdOnShowing(showingId);

            seat = seatRepository.save(seat);
            showing.getSeats().add(seat);
        }

        showingRepository.save(showing);
        return new ShowingResponse(showing);
//        LocalDateTime ldt = addShowingRequest.getTimeAndDate();
    }
}
