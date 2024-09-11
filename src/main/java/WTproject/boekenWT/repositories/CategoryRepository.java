package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.Category;
import org.springframework.data.repository.CrudRepository;

// CRUD refers Create, Read, Update, Delete
public interface CategoryRepository extends CrudRepository<Category, Integer> { }
