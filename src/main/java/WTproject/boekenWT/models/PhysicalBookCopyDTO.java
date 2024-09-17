package WTproject.boekenWT.models;

import java.util.Date;

public class PhysicalBookCopyDTO {
    PhysicalBookCopy copy;
    Date purchaseDate;
    PhysicalCondition condition;
    BorrowingStatus borrowingStatus;

    public PhysicalBookCopy getPhysicalBookCopy() {
        return copy;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public PhysicalCondition getCondition() {
        return condition;
    }

    public BorrowingStatus getBorrowingStatus() {
        return borrowingStatus;
    }
}
