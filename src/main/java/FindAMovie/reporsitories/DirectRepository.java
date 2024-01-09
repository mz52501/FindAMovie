package FindAMovie.reporsitories;

import FindAMovie.models.Direct;
import FindAMovie.models.DirectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectRepository extends JpaRepository<Direct, DirectId> {
}