package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private PhysicalBook physicalBook;

    @Column
    private Date requestDate;

    @Column
    private int requestStatusId;

}
