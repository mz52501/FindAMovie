package FindAMovie.reporsitories;

import FindAMovie.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    boolean existsByNameAndSurname(String name, String surname);
    Person getByImage(String image);
}