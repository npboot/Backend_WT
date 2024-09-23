package WTproject.boekenWT.models;

import jakarta.persistence.*;

@Entity
@Table(name="REQUESTSTATUS")
public class RequestStatus {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestStatusId;

    @Column
    private String requestStatusType;

    //getters and setters
    public int getRequestStatusId() {
        return requestStatusId;
    }

    public String getRequestStatusType() {
        return requestStatusType;
    }

    public void setRequestStatusType(String requestStatusType) {
        requestStatusType = requestStatusType;
    }
}
