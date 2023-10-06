package scrumbledore.kinoproject.project.theater.service;

import org.springframework.stereotype.Service;
import scrumbledore.kinoproject.project.theater.dto.TheaterRequest;
import scrumbledore.kinoproject.project.theater.dto.TheaterResponse;
import scrumbledore.kinoproject.project.theater.enity.Theater;
import scrumbledore.kinoproject.project.theater.repository.TheaterRepository;

import java.util.List;

@Service
public class TheaterService {

    TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }


    public List <TheaterResponse> getAllTheaters(){
        List <Theater> theaters = theaterRepository.findAll();
        List <TheaterResponse> theaterResponses = theaters.stream().map(theater ->
                new TheaterResponse(theater)).toList();
        return theaterResponses;
    }

    public TheaterResponse addTheater(TheaterRequest theaterRequest){
        Theater theater = TheaterRequest.getTheaterEntity(theaterRequest);
        theater = theaterRepository.save(theater);
        return new TheaterResponse(theater);
    }

}
