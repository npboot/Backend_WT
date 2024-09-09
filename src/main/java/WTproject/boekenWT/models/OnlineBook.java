package WTproject.boekenWT.models;

import jakarta.persistence.*;

@Entity
@Table(name="ONLINEBOOK")
public class OnlineBook {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ebookId;

    @OneToOne
    @JoinColumn(name = "isbn")
    private Book book;

    @Column
    private String filePath;

    @Column
    private boolean archived;

    //getters and setters
    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean getArchived() {
        return this.archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

}
