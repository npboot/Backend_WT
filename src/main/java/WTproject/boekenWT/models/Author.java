package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name="AUTHOR")
public class Author {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @Column
    private String name;

    @ManyToMany(mappedBy = "isbn")
    private Set<Book> books;


    //getters and setters
    public int getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
