package WTproject.boekenWT.models;

import java.util.Date;

public class PhysicalBookCopyDTO {
    PhysicalBookCopy copy;
    Date purchaseDate;
    Enums.Condition condition;
    Enums.BorrowingStatus borrowingStatus;

    public PhysicalBookCopy getPhysicalBookCopy() {
        return copy;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Enums.Condition getCondition() {
        return condition;
    }

    public Enums.BorrowingStatus getBorrowingStatus() {
        return borrowingStatus;
    }
}
