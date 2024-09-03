package WTproject.boekenWT.models;

import org.springframework.data.repository.CrudRepository;

// CRUD refers Create, Read, Update, Delete
public interface BookRepository extends CrudRepository<Book, Integer> { }
