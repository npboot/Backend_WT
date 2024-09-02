package WTproject.boekenWT.models;

public class Enums {

    public enum SourceType {
        VIDEO,
        ONLINE_BOOK,
        PHYSICAL_BOOK,
    }

    public enum Category {
        PROGRAMMING,
    }

    public enum Condition {
        NEW,
        USED,
        DAMAGED,
        UNUSABLE,
        LOST,
    }

    public enum BorrowingStatus {
        AVAILABLE,
        REQUESTED,
        APPROVED,
        DENIED,
        OUT_OF_ORDER,
    }
}
