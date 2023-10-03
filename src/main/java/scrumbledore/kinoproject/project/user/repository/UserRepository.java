package scrumbledore.kinoproject.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scrumbledore.kinoproject.project.user.entity.User;


public interface UserRepository extends JpaRepository<User, String> {

}
