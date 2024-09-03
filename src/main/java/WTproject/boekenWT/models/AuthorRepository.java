package WTproject.boekenWT.models;

import org.springframework.data.repository.CrudRepository;

// CRUD refers Create, Read, Update, Delete
public interface AuthorRepository extends CrudRepository<Author, Integer> { }
