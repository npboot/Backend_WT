package WTproject.boekenWT.repositories;

import org.springframework.data.repository.CrudRepository;

import WTproject.boekenWT.models.Book;

import java.util.List;

// CRUD refers Create, Read, Update, Delete
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findBookByIsbn(int isbn);
}
