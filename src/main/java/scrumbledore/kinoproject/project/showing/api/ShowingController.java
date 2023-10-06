package scrumbledore.kinoproject.project.showing.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import scrumbledore.kinoproject.project.showing.dto.AddShowingRequest;
import scrumbledore.kinoproject.project.showing.dto.ShowingResponse;
import scrumbledore.kinoproject.project.showing.entity.Showing;
import scrumbledore.kinoproject.project.showing.service.ShowingService;

@RestController
@RequestMapping("api/showings")
public class ShowingController {

    ShowingService showingService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShowingResponse addShowing(@RequestBody AddShowingRequest body){
        return showingService.addShowing(body);
    }




}
