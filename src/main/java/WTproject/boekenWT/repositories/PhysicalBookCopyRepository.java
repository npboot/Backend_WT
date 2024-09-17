package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.PhysicalBookCopy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// CRUD refers Create, Read, Update, Delete
public interface PhysicalBookCopyRepository extends CrudRepository<PhysicalBookCopy, Integer> {
    @Query(
            value = "SELECT pbc.* FROM PHYSICALBOOKCOPY pbc INNER JOIN PHYSICALBOOK pb ON pbc.book_id = pb.book_id INNER JOIN BOOK b ON pb.isbn = b.isbn WHERE b.isbn = ?1",
            nativeQuery = true)
    List<PhysicalBookCopy> findCopiesByIsbn(int isbn);
}

