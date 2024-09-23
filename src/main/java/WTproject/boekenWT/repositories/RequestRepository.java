package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Integer> {

    @Query(
            value = "SELECT r.* FROM Request r WHERE r.user_id = ?1",
            nativeQuery = true)
    List<Request> findRequestsByUserId(int userId);

    @Query(
            value = "SELECT r.* FROM Request r WHERE r.p_book_id = ?1 AND r.request_status_id = ?2 ORDER BY request_date",
            nativeQuery = true)
    List<Request> findRequestsByPBookIdAndPending(int pBookId, int requestStatusId);
}
