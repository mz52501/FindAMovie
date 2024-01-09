package FindAMovie.reporsitories;

import FindAMovie.models.Review;
import FindAMovie.models.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
}