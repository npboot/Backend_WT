package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.Borrowing;
import WTproject.boekenWT.models.BorrowingStatus;
import org.springframework.data.repository.CrudRepository;

public interface BorrowingStatusRepository extends CrudRepository<BorrowingStatus, Integer> {
}
