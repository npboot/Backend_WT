package WTproject.boekenWT.models;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name="PHYSICALBOOKCOPY")
public class PhysicalBookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CopyId;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private PhysicalBook physicalBook;

    @Column
    private Enums.Condition physicalCondition;

    @Column
    private Enums.BorrowingStatus status;

    @Column
    private DateTimeFormat purchaseDate;

    @Column
    private boolean archived;
    

    //getters and setters
    public int getCopyId() {
        return this.CopyId;
    }

    public void setCopyId(int CopyId) {
        this.CopyId = CopyId;
    }

    public PhysicalBook getPhysicalBook() {
        return this.physicalBook;
    }

    public void setPhysicalBook(PhysicalBook physicalBook) {
        this.physicalBook = physicalBook;
    }

    public Enums.Condition getPhysicalCondition() {
        return this.physicalCondition;
    }

    public void setPhysicalCondition(Enums.Condition physicalCondition) {
        this.physicalCondition = physicalCondition;
    }

    public Enums.BorrowingStatus getStatus() {
        return this.status;
    }

    public void setStatus(Enums.BorrowingStatus status) {
        this.status = status;
    }

    public DateTimeFormat getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(DateTimeFormat purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean getArchived() {
        return this.archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

}
