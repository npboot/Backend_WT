package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="AVAILABILITY")
public class Availability {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private int availabilityId;

    @Column
    private String availabilityType;

    @OneToMany(mappedBy = "copyId")
    private Set<PhysicalBookCopy> physicalBookCopies;

    // Getters and setters
    public int getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(int availabilityId) {
        this.availabilityId = availabilityId;
    }

    public String getAvailabilityType() {
        return availabilityType;
    }

    public void setAvailabilityType(String availabilityType) {
        this.availabilityType = availabilityType;
    }

}
