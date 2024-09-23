package WTproject.boekenWT.models.DTO;

import WTproject.boekenWT.models.Availability;
import WTproject.boekenWT.models.PhysicalBook;
import WTproject.boekenWT.models.PhysicalCondition;

import java.util.Date;
import java.util.List;

public class PhysicalBookCopiesDTO {
    PhysicalBook physicalBook;
    List<Date> purchaseDate;
    List<PhysicalCondition> condition;
    List<Availability> availability;

    public PhysicalBook getPhysicalBook() {
        return physicalBook;
    }

    public void setPhysicalBook(PhysicalBook physicalBook) {
        this.physicalBook = physicalBook;
    }

    public List<Date> getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(List<Date> purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<PhysicalCondition> getPhysicalCondition() {
        return condition;
    }

    public void setPhysicalCondition(List<PhysicalCondition> condition) {
        this.condition = condition;
    }

    public List<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Availability> availability) {
        this.availability = availability;
    }
}
