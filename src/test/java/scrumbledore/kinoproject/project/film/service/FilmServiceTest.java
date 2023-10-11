package scrumbledore.kinoproject.project.film.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import scrumbledore.kinoproject.project.film.api_facade.OmdbFacade;
import scrumbledore.kinoproject.project.film.entity.Film;
import scrumbledore.kinoproject.project.film.repository.FilmRepository;
import scrumbledore.kinoproject.security.TestUtils;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FilmServiceTest {

    @Autowired
    FilmRepository filmRepository;
    FilmService filmService;

    @BeforeAll
    public static void iniSetUp(){
        System.setProperty(TestUtils.h2UrlName, TestUtils.h2UrlValue);
        System.setProperty(TestUtils.h2UsernameName, TestUtils.h2UsernameValue);
        System.setProperty(TestUtils.h2PassName, TestUtils.h2PassValue);
    }

    @AfterAll
    public static void tearDown(){
        System.clearProperty(TestUtils.h2UrlName);
        System.clearProperty(TestUtils.h2UsernameName);
        System.clearProperty(TestUtils.h2PassName);
    }

    @BeforeEach
    void setUp() {
        filmService = new FilmService(filmRepository);
    }

    //@Test
    void testAddFilm() throws JsonProcessingException {
        Film film = filmService.addFilm("tt0068646");
        assertEquals(1, filmService.getAllFilms().size());
    }
}