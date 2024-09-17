package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    // query methods
    Optional<User> findByName(String name);
    Boolean existsByName(String name);
}
