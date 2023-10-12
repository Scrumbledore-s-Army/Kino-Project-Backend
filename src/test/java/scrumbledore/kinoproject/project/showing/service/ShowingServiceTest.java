package scrumbledore.kinoproject.project.showing.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import scrumbledore.kinoproject.project.film.entity.Film;
import scrumbledore.kinoproject.project.film.repository.FilmRepository;
import scrumbledore.kinoproject.project.film.service.FilmService;
import scrumbledore.kinoproject.project.seat.repository.SeatRepository;
import scrumbledore.kinoproject.project.showing.dto.AddShowingRequest;
import scrumbledore.kinoproject.project.showing.repository.ShowingRepository;
import scrumbledore.kinoproject.project.theater.dto.TheaterRequest;
import scrumbledore.kinoproject.project.theater.enity.Theater;
import scrumbledore.kinoproject.project.theater.repository.TheaterRepository;
import scrumbledore.kinoproject.project.theater.service.TheaterService;
import scrumbledore.kinoproject.security.TestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

    @SpringBootTest
    class ShowingServiceTest {

    @MockBean
    ShowingRepository showingRepository;


    ShowingService showingService;

    @Autowired
    FilmService filmService;


        TheaterService theaterService;

    @Autowired
        TheaterRepository theaterRepository;

    @Autowired
        FilmRepository filmRepository;
    @Autowired
        SeatRepository seatRepository;

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
        theaterService = new TheaterService(theaterRepository);
        showingService = new ShowingService(filmRepository, theaterRepository, showingRepository,seatRepository);
    }

    @Test
        public void testAddShowing(){
        Film film = makeFilm();
        Theater t1 = Theater.builder()
                .seatCount(400).rowLength(10).build();
        TheaterRequest theaterRequest = new TheaterRequest(t1);

        System.out.println(t1.getId());
        theaterService.addTheater(theaterRequest);

        LocalDateTime localDateTime = LocalDateTime.parse("2023-10-24T10:10:00");

        AddShowingRequest addShowingRequest = new AddShowingRequest(film.getId(),t1.getId(),
                250.25, localDateTime);
        showingService.addShowing(addShowingRequest);

        assertEquals(1, showingService.getAllShowings(true).size());
    }

        public Film makeFilm() {
            Film film = new Film();
            film.setImdbID("tt7286456");
            film.setTitle("Joker");
            film.setYear("2019");
            film.setRated("R");
            film.setReleased("04 Oct 2019");
            film.setRuntime("122 min");
            film.setGenre("Crime, Drama, Thriller");
            film.setDirector("Todd Phillips");
            film.setWriter("Todd Phillips, Scott Silver");
            film.setActors("Joaquin Phoenix, Robert De Niro, Zazie Beetz, Frances Conroy");
            film.setPlot("In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.");
            film.setPoster("https://m.media-amazon.com/images/M/MV5BMWMwMGU5MmUtMzQyZC00M2M0LWE5MjAtMzY2MzY2MzY2MzY2XkEyXkFqcGdeQXVyMTA4NjE0NjEy._V1_SX300.jpg");
            film.setMetascore("59");
            film.setImdbRating("8.5");
            film.setImdbVotes("1,069,017");
            film.setImdbID("tt7286456");
            film.setWebsite("N/A");
            film.setResponse("True");
            return film;
        }
}