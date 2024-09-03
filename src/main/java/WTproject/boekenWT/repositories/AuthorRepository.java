package WTproject.boekenWT.repositories;

import org.springframework.data.repository.CrudRepository;

import WTproject.boekenWT.models.Author;

// CRUD refers Create, Read, Update, Delete
public interface AuthorRepository extends CrudRepository<Author, Integer> { }
