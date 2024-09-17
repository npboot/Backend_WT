package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="REQUEST")
public class Request {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pBookId")
    private PhysicalBook physicalBook;

    @Column
    private Date requestDate;

    @ManyToOne
    @JoinColumn(name = "requestStatusId")
    private RequestStatus requestStatus;

    //getters and setters
    public int getRequestId() {
        return requestId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PhysicalBook getPhysicalBook() {
        return physicalBook;
    }

    public void setPhysicalBook(PhysicalBook physicalBook) {
        this.physicalBook = physicalBook;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
