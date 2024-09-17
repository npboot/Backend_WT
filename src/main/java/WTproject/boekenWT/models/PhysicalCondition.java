package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="PHYSICALCONDITION")
public class PhysicalCondition {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int physicalConditionId;

    @Column
    private String conditionType;

    @OneToMany(mappedBy = "copyId")
    private Set<PhysicalBookCopy> physicalBookCopies;

    // Getters and setters
    public int getPhysicalConditionId() {
        return physicalConditionId;
    }

    public void setPhysicalConditionId(int physicalConditionId) {
        this.physicalConditionId = physicalConditionId;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

}
