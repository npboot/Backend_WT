package WTproject.boekenWT.models;

import jakarta.persistence.*;

@Entity
@Table(name="USER")
public class User {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private int age;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "userTypeId")
    private UserType userType;

}
