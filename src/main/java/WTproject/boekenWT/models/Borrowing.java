package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="BORROWING")
public class Borrowing {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowingId;

    @OneToOne
    @JoinColumn(name = "requestId")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "copyId")
    private PhysicalBookCopy physicalBookCopy;

    @Column
    private Date startDate;

    @Column
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "borrowingStatusId")
    private BorrowingStatus borrowingStatus;

    //getters and setters
    public int getBorrowingId() {
        return borrowingId;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public PhysicalBookCopy getPhysicalBookCopy() {
        return physicalBookCopy;
    }

    public void setPhysicalBookCopy(PhysicalBookCopy physicalBookCopy) {
        this.physicalBookCopy = physicalBookCopy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BorrowingStatus getBorrowingStatus() {
        return borrowingStatus;
    }

    public void setBorrowingStatus(BorrowingStatus borrowingStatus) {
        this.borrowingStatus = borrowingStatus;
    }
}
