package scrumbledore.kinoproject.project.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scrumbledore.kinoproject.security.entity.UserWithRoles;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User extends UserWithRoles {



    @Column(nullable = true, length = 50)
    private String firstName;

    @Column(nullable = true, length = 50)
    private String lastName;

    @Column(nullable = true, length = 50)
    private String street;

    @Column(nullable = true, length = 50)
    private String city;

    @Column(nullable = true, length = 10)
    private String zip;

// Constructors
    public User(String username, String password, String email, String firstName, String lastName, String street, String city, String zip) {
        super(username, password, email); // Call the constructor of the superclass
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

}
 