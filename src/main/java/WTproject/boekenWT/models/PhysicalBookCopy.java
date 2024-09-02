package WTproject.boekenWT.models;

import jakarta.persistence.*;

@Entity
@Table(name="PHYSICALBOOKCOPY")
public class PhysicalBookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int physicalBookCopyId;

    @ManyToOne
    @JoinColumn(name = "physicalBookId")
    private PhysicalBook physicalBook;

    @Column
    private String physicalCondition;

    @Column
    private String borrowingStatus;

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

    public String getPhysicalCondition() {
        return physicalCondition;
    }

    public void setPhysicalCondition(String physicalCondition) {
        this.physicalCondition = physicalCondition;
    }

    public String getBorrowingStatus() {
        return borrowingStatus;
    }

    public void setBorrowingStatus(String borrowingStatus) {
        this.borrowingStatus = borrowingStatus;
    }
}
