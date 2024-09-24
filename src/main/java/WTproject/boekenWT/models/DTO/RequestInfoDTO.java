package WTproject.boekenWT.models.DTO;

import WTproject.boekenWT.models.Author;
import WTproject.boekenWT.models.Request;

import java.util.Date;
import java.util.StringJoiner;

public class RequestInfoDTO {

    private int isbn;
    private String status;
    private String title;
    private String authorName;
    private Date requestDate;

    public RequestInfoDTO(Request request) {
        isbn = request.getPhysicalBook().getBook().getIsbn();
        status = request.getRequestStatus().getRequestStatusType();
        title = request.getPhysicalBook().getBook().getTitle();
        StringJoiner authorString = new StringJoiner(", ");
        for(Author a:request.getPhysicalBook().getBook().getAuthors()) {
            authorString.add(a.getName());
        };
        authorName = authorString.toString();
        requestDate = request.getRequestDate();
    }

    //getters en setters
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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
