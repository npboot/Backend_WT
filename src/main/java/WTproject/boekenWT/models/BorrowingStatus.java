package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="BORROWINGSTATUS")
public class BorrowingStatus {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowingStatusId;

    @Column
    private String borrowingStatusType;

    @OneToMany(mappedBy = "copyId")
    private Set<PhysicalBookCopy> physicalBookCopies;

    // Getters and setters
    public int getBorrowingStatusId() {
        return borrowingStatusId;
    }

    public void setBorrowingStatusId(int borrowingStatusId) {
        this.borrowingStatusId = borrowingStatusId;
    }

    public String getBorrowingStatusType() {
        return borrowingStatusType;
    }

    public void setBorrowingStatusType(String borrowingStatusType) {
        this.borrowingStatusType = borrowingStatusType;
    }
}
