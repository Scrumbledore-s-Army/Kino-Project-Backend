package scrumbledore.kinoproject.project.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import scrumbledore.kinoproject.project.user.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    String username;
    String email;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;
    LocalDateTime created;
    LocalDateTime edited;

    public UserResponse(User m) {
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.created = m.getCreated();
        this.edited = m.getEdited();
    }
}


