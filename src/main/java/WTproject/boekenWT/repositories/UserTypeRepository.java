package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.UserType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserTypeRepository extends CrudRepository<UserType, Integer> {
    Optional<UserType> findByUserTypeName(String userTypeName);
}
