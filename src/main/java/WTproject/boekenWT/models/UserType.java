package WTproject.boekenWT.models;

import jakarta.persistence.*;

@Entity
@Table(name="USERTYPE")
public class UserType {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userTypeId;

    @Column
    private String userTypeName;

}
