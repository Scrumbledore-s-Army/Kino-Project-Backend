package scrumbledore.kinoproject.project.user.dto;

import lombok.*;
import scrumbledore.kinoproject.project.user.entity.User;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {
    String username;
    String email;
    String password;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;

    public static User getUserEntity(UserRequest m) {
        return new User(m.username, m.getPassword(), m.getEmail(), m.firstName, m.lastName, m.getStreet(), m.getCity(), m.getZip());
    }

    // User to UserRequest conversion
    public UserRequest(User m) {
        this.username   = m.getUsername();
        this.password   = m.getPassword();
        this.firstName  = m.getFirstName();
        this.lastName   = m.getLastName();
        this.email      = m.getEmail();
        this.street     = m.getStreet();
        this.city       = m.getCity();
        this.zip        = m.getZip();
    }
}
