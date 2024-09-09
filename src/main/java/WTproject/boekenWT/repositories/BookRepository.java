package WTproject.boekenWT.repositories;

import org.springframework.data.repository.CrudRepository;

import WTproject.boekenWT.models.Book;

// CRUD refers Create, Read, Update, Delete
public interface BookRepository extends CrudRepository<Book, Integer> { }
