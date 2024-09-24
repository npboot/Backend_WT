package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    // query methods
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
