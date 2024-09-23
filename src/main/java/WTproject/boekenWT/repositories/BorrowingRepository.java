package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.Borrowing;
import WTproject.boekenWT.models.PhysicalBookCopy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BorrowingRepository extends CrudRepository<Borrowing, Integer> {

    @Query(
            value = "SELECT b.* FROM Borrowing b INNER JOIN REQUEST r ON b.request_id = r.request_id INNER JOIN USER u ON r.user_id = u.user_id WHERE u.user_id = ?1",
            nativeQuery = true)
    List<Borrowing> findBorrowingsByUserId(int userId);

    @Query(
            value = "SELECT b.* FROM Borrowing b INNER JOIN PHYSICALBOOKCCOPY pbc ON b.copy_id = pbc.copy_id WHERE pbc.book_id = ?1",
            nativeQuery = true)
    List<Borrowing> findBorrowingsByPBookId(int pBookId);

}
