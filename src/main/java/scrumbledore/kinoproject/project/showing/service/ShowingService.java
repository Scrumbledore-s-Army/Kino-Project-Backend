package scrumbledore.kinoproject.project.showing.service;

import org.springframework.stereotype.Service;
import scrumbledore.kinoproject.project.film.entity.Film;
import scrumbledore.kinoproject.project.film.repository.FilmRepository;
import scrumbledore.kinoproject.project.seat.entity.Seat;
import scrumbledore.kinoproject.project.showing.dto.AddShowingRequest;
import scrumbledore.kinoproject.project.showing.dto.ShowingRequest;
import scrumbledore.kinoproject.project.showing.dto.ShowingResponse;
import scrumbledore.kinoproject.project.showing.entity.Showing;
import scrumbledore.kinoproject.project.showing.repository.ShowingRepository;
import scrumbledore.kinoproject.project.theater.enity.Theater;
import scrumbledore.kinoproject.project.theater.repository.TheaterRepository;

import java.time.LocalDateTime;

@Service
public class ShowingService {

    FilmRepository filmRepository;
    TheaterRepository theaterRepository;

    ShowingRepository showingRepository;

    public ShowingResponse addShowing(AddShowingRequest addShowingRequest){
        Showing showing = new Showing();

        int filmId = addShowingRequest.getFilmId();
        int theaterId = addShowingRequest.getTheaterId();

        Film film = filmRepository.findById(filmId).orElseThrow();
        showing.setFilm(film);

        Theater theater = theaterRepository.findById(theaterId).orElseThrow();
        showing.setTheater(theater);

        showing.setTicketPrice(addShowingRequest.getTicketPrice());
        showing.setTimeAndDate(addShowingRequest.getTimeAndDate());

        for (int i = 0; i < theater.getSeatCount(); i++) {
            showing.getSeats().add(new Seat());
        }

        showingRepository.save(showing);
        return new ShowingResponse(showing);
//        LocalDateTime ldt = addShowingRequest.getTimeAndDate();
    }
}
