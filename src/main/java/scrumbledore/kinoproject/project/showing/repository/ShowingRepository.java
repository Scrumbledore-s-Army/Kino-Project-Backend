package scrumbledore.kinoproject.project.showing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scrumbledore.kinoproject.project.showing.entity.Showing;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Integer> {
}
