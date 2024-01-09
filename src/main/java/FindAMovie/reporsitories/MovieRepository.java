package FindAMovie.reporsitories;

import FindAMovie.models.Movie;
import FindAMovie.models.User;
import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(
            value = "SELECT * FROM MOVIE u WHERE u.releasedate > CURRENT_DATE ",
            nativeQuery = true)
    List<Movie> findAllNewMovies();
}