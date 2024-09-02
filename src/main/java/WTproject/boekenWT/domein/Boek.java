package WTproject.boekenWT.domein;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Boek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String boekName;



}
