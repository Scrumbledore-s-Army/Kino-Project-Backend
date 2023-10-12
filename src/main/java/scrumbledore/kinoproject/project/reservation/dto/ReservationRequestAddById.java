package scrumbledore.kinoproject.project.reservation.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequestAddById {
 private String username;
 private Integer showingId;
 private Integer[] seatIds;
}
