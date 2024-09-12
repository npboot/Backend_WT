package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.PhysicalBookCopy;
import org.springframework.data.repository.CrudRepository;

// CRUD refers Create, Read, Update, Delete
public interface PhysicalBookCopyRepository extends CrudRepository<PhysicalBookCopy, Integer> { }

