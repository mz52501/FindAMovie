package FindAMovie.reporsitories;

import FindAMovie.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
GenreRepository extends JpaRepository<Genre, Integer> {
}