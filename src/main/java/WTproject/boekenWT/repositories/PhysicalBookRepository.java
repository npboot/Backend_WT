package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.PhysicalBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// CRUD refers Create, Read, Update, Delete
public interface PhysicalBookRepository extends CrudRepository<PhysicalBook, Integer> {
    @Query(
//            value = "SELECT pb.* FROM PHYSICALBOOK pb WHERE pb.isbn = ?1",
            value = "SELECT pb.* FROM PHYSICALBOOK pb INNER JOIN BOOK b ON pb.isbn = b.isbn WHERE b.isbn = ?1",
            nativeQuery = true)
    PhysicalBook findPhysicalBookByIsbn(int isbn);

//    @Query(
////            value = "SELECT pb.* FROM PHYSICALBOOK pb WHERE pb.isbn = ?1",
//            value = "EXISTS( SELECT pb.* FROM PHYSICALBOOK pb INNER JOIN BOOK b ON pb.isbn = b.isbn WHERE b.isbn = ?1)",
//            nativeQuery = true)
//    boolean existsPhysicalBookByIsbn(int isbn);

    @Query(
//            value = "SELECT pb.* FROM PHYSICALBOOK pb WHERE pb.isbn = ?1",
            value = "SELECT CASE WHEN EXISTS (SELECT pb.* FROM PHYSICALBOOK pb INNER JOIN BOOK b ON pb.isbn = b.isbn WHERE b.isbn = ?1) THEN 1 ELSE 0 END",
            nativeQuery = true)
    int existsPhysicalBookByIsbn(int isbn);


}
