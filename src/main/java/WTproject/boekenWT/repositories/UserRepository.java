package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.Author;
import WTproject.boekenWT.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
