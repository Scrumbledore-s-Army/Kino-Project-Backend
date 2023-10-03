package scrumbledore.kinoproject.project.film.api;




import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scrumbledore.kinoproject.project.film.entity.Film;
import scrumbledore.kinoproject.project.film.service.FilmService;

@RestController
@RequestMapping("api/films")
public class FilmController {

    FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @RequestMapping("/imdbid/{imdbId}")
    public Film getFilm(@PathVariable String imdbId) {
        return filmService.getFilmByImdbId(imdbId);
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/{imdbId}")
    public Film addfilm(@PathVariable String imdbId) throws JsonProcessingException {
        return filmService.addFilm(imdbId);
    }


}
