package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.Request;
import WTproject.boekenWT.models.User;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer> {
}
