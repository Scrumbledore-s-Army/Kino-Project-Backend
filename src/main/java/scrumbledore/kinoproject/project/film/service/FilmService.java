package scrumbledore.kinoproject.project.film.service;


import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import scrumbledore.kinoproject.project.film.api_facade.OmdbFacade;
import scrumbledore.kinoproject.project.film.dto.FilmOmdbResponse;
import scrumbledore.kinoproject.project.film.dto.FilmResponse;
import scrumbledore.kinoproject.project.film.entity.Film;
import scrumbledore.kinoproject.project.film.repository.FilmRepository;

import java.util.List;

@Service
public class FilmService {

    FilmRepository filmRepository;

//    @Autowired
//    AzureTranslate translator;

    @Autowired
    OmdbFacade omdbFacade;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
    public FilmResponse getFilmById(int id){
        Film film = filmRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Film with this ID does not exist"));
        return new FilmResponse(film);
    }

    public Film getFilmByImdbId(String imdbId) {
        return filmRepository.findByImdbID(imdbId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
    }


    public Film addFilm(String imdbId) throws JsonProcessingException {
        FilmOmdbResponse dto = omdbFacade.getFilm(imdbId);
        //String dkPlot = translator.translate(dto.getPlot());

        Film film = Film.builder()
                .title(dto.getTitle())
                .year(dto.getYear())
                .rated(dto.getRated())
                .released(dto.getReleased())
                .runtime(dto.getRuntime())
                .genre(dto.getGenre())
                .director(dto.getDirector())
                .writer(dto.getWriter())
                .actors(dto.getActors())
                .metascore(dto.getMetascore())
                .imdbRating(dto.getImdbRating())
                .imdbVotes(dto.getImdbVotes())
                .website(dto.getWebsite())
                .response(dto.getResponse())
                .plot(dto.getPlot())
                //.plotDK(dkPlot)
                .poster(dto.getPoster())
                .imdbID(dto.getImdbID())
                .build();
        try {
            filmRepository.save(film);
            return film;
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not add movie");
        }
    }
}
