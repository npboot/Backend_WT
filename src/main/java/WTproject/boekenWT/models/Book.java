package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BOOK")
public class Book {
    //attributes
    @Id
    private int isbn;

    @ManyToMany
    @JoinTable(
    name = "AUTHOR_BOOK", 
    joinColumns = @JoinColumn(name = "isbn"), 
    inverseJoinColumns = @JoinColumn(name = "authorId"))
    private Set<Author> authors = new HashSet<>();

    @Column
    private String title;

    @Column String summary;

    @Column
    private Year year;

    @Column
    private Set<Enums.Category> categories;

    @Column
    private boolean isOnline;

    @Column
    private boolean isPhysical;

    @OneToOne(mappedBy = "book")
    private PhysicalBook physicalBook;

    @OneToOne(mappedBy = "book")
    private OnlineBook onlineBook;

    //getters and setters
    public int getIsbn() {
        return this.isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Year getYear() {
        return this.year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Set<Enums.Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Enums.Category> categories) {
        this.categories = categories;
    }

    public boolean getIsOnline() {
        return this.isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public boolean getIsPhysical() {
        return this.isPhysical;
    }

    public void setIsPhysical(boolean isPhysical) {
        this.isPhysical = isPhysical;
    }

}
