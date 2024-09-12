package WTproject.boekenWT.models;

import java.time.Year;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name="PHYSICALBOOKCOPY")
public class PhysicalBookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int copyId;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private PhysicalBook physicalBook;

    @ManyToOne
    @JoinColumn(name = "physicalConditionId")
    private PhysicalCondition physicalCondition;

    @ManyToOne
    @JoinColumn(name = "borrowingStatusId")
    private BorrowingStatus borrowingStatus;

    @Column
    private Date purchaseDate;

    @Column
    private boolean archived;
    

    //getters and setters
    public int getCopyId() {
        return this.copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    public PhysicalBook getPhysicalBook() {
        return this.physicalBook;
    }

    public void setPhysicalBook(PhysicalBook physicalBook) {
        this.physicalBook = physicalBook;
    }

    public PhysicalCondition getPhysicalCondition() {
        return this.physicalCondition;
    }

    public void setPhysicalCondition(PhysicalCondition physicalCondition) {
        this.physicalCondition = physicalCondition;
    }

    public BorrowingStatus getBorrowingStatus() {
        return this.borrowingStatus;
    }

    public void setBorrowingStatus(BorrowingStatus borrowingStatus) {
        this.borrowingStatus = borrowingStatus;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean getArchived() {
        return this.archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

}
