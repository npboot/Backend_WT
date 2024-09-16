package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.PhysicalBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// CRUD refers Create, Read, Update, Delete
public interface PhysicalBookRepository extends CrudRepository<PhysicalBook, Integer> {
    @Query(
//            value = "SELECT pb.* FROM PHYSICALBOOK pb WHERE pb.isbn = ?1",
            value = "SELECT pb.* FROM PHYSICALBOOK pb INNER JOIN BOOK b ON pb.isbn = b.isbn WHERE b.isbn = ?1",
            nativeQuery = true)
    PhysicalBook findPhysicalBookByIsbn(int isbn);
}
