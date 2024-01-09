package FindAMovie.reporsitories;

import FindAMovie.models.Type;
import FindAMovie.models.TypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, TypeId> {
}