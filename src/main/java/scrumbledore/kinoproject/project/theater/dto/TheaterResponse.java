package scrumbledore.kinoproject.project.theater.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scrumbledore.kinoproject.project.theater.enity.Theater;

@Getter
@Setter
@NoArgsConstructor
public class TheaterResponse {
    private int id;
    private Integer seatCount;
    public TheaterResponse(Theater theater) {
        this.id = theater.getId();
        this.seatCount = theater.getSeatCount();}
}
