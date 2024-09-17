package WTproject.boekenWT.models;

import java.util.Date;

public class PhysicalBookCopyDTO {
    PhysicalBookCopy copy;
    Date purchaseDate;
    PhysicalCondition condition;
    Availability availability;

    public PhysicalBookCopy getPhysicalBookCopy() {
        return copy;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public PhysicalCondition getCondition() {
        return condition;
    }

    public Availability getAvailability() {
        return availability;
    }
}
