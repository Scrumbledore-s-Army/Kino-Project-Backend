package scrumbledore.kinoproject.project.film.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import scrumbledore.kinoproject.project.film.entity.Film;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    Optional<Film> findByImdbId(String imdbId);
}
