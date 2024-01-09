package FindAMovie.reporsitories;

import FindAMovie.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmailOrUsername(String email, String username);

    Optional<User> findByUsername(String username);
    User getByUsername(String username);
}