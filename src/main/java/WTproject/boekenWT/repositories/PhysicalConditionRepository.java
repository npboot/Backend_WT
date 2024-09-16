package WTproject.boekenWT.repositories;

import WTproject.boekenWT.models.PhysicalCondition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//            value = "SELECT pc.* FROM PHYSICALCONDITION pc INNER JOIN PHYSICALBOOKCOPY pbc ON pc.copy_id = pbc.copy_id WHERE pbc.copy_id = ?1",
public interface PhysicalConditionRepository extends CrudRepository<PhysicalCondition, Integer> {
    @Query(
            value = "SELECT pc.* FROM PHYSICALCONDITION pc INNER JOIN PHYSICALBOOKCOPY pbc ON pc.physical_condition_id = pbc.physical_condition_id WHERE pbc.copy_id = ?1",
            nativeQuery = true)
    PhysicalCondition findConditionByCopyId(int copyId);
}
