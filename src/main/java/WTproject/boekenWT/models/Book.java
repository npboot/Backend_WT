package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.time.Year;

@Entity
@Table(name="BOOK")
public class Book {

    @Id
    private String isbn;

    @Column
    private String title;

    @ManyToOne // Test, dit moet ManyToMany zijn
    @JoinColumn(name = "authorId")
    private Author author;

    @OneToOne(mappedBy = "book")
    private PhysicalBook physicalBook;

    @Column
    private Year year;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
