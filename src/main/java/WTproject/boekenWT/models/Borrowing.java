package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowingId;

//    @OneToOne
//    @JoinColumn()

    @Column
    private int copyId;

    @Column
    private Date startDate;

    @Column
    private Date returnDate;

    @Column
    private int borrowingStatusId;

}
