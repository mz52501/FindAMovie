package FindAMovie.reporsitories;

import FindAMovie.models.Act;
import FindAMovie.models.ActId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActRepository extends JpaRepository<Act, ActId> {
}