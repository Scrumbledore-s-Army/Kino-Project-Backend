package scrumbledore.kinoproject.project.theater.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scrumbledore.kinoproject.project.theater.enity.Theater;

@Getter
@Setter
@NoArgsConstructor
public class TheaterRequest {
    private Integer seatCount;

    public static Theater getTheaterEntity(TheaterRequest theaterRequest) {
        return new Theater().builder()
                .seatCount(theaterRequest.getSeatCount())
                .build();
    }

    public TheaterRequest(Theater theater) {
        this.seatCount = theater.getSeatCount();
    }
}
