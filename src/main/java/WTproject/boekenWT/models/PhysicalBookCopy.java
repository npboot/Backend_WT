package WTproject.boekenWT.models;

import jakarta.persistence.*;

@Entity
public class PhysicalBookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int physicalBookCopyId;

    @ManyToOne
    @JoinColumn(name = "physicalBookId")
    private PhysicalBook physicalBook;

    @Column
    private Enums.Condition condition;

    @Column
    private Enums.BorrowingStatus borrowingStatus;

    public int getPhysicalBookCopyId() {
        return physicalBookCopyId;
    }

    public void setPhysicalBookCopyId(int physicalBookCopyId) {
        this.physicalBookCopyId = physicalBookCopyId;
    }

    public PhysicalBook getPhysicalBook() {
        return physicalBook;
    }

    public void setPhysicalBook(PhysicalBook physicalBook) {
        this.physicalBook = physicalBook;
    }

    public Enums.Condition getCondition() {
        return condition;
    }

    public void setCondition(Enums.Condition condition) {
        this.condition = condition;
    }

    public Enums.BorrowingStatus getBorrowingStatus() {
        return borrowingStatus;
    }

    public void setBorrowingStatus(Enums.BorrowingStatus borrowingStatus) {
        this.borrowingStatus = borrowingStatus;
    }
}
