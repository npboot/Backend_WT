package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.PhysicalBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// CRUD refers Create, Read, Update, Delete
public interface PhysicalBookRepository extends CrudRepository<PhysicalBook, Integer> { }


