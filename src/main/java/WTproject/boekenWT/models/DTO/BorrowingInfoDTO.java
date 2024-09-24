package WTproject.boekenWT.models.DTO;

import WTproject.boekenWT.models.Author;
import WTproject.boekenWT.models.Borrowing;

import java.util.Date;
import java.util.StringJoiner;

public class BorrowingInfoDTO {
    private int isbn;
    private String status;
    private String title;
    private String authorName;
    private int copyID;
    private String note;
    private String borrowingDate;
    private Date returnDate;
    private int borrowingId;

    public BorrowingInfoDTO(Borrowing borrowing) {
        isbn = borrowing.getPhysicalBookCopy().getPhysicalBook().getBook().getIsbn();
        status = borrowing.getBorrowingStatus().getBorrowingStatusType();
        title = borrowing.getPhysicalBookCopy().getPhysicalBook().getBook().getTitle();
        StringJoiner authorString = new StringJoiner(", ");
        for(Author a:borrowing.getPhysicalBookCopy().getPhysicalBook().getBook().getAuthors()) {
            authorString.add(a.getName());
        };
        authorName = authorString.toString();
        copyID = borrowing.getPhysicalBookCopy().getCopyId();
        note =  "";
        borrowingDate = borrowing.getStartDate().toString();
        returnDate = borrowing.getReturnDate();
        borrowingId = borrowing.getBorrowingId();
    }

    //getters and setter
    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getCopyID() {
        return copyID;
    }

    public void setCopyID(int copyID) {
        this.copyID = copyID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(String borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getBorrowingId() { return borrowingId;}

    public void setBorrowingId(int borrowingId) { this.borrowingId = borrowingId;}
}
