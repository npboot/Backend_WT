package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name="AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @Column
    private String name;

    @OneToMany(mappedBy = "author")
    private Set<Book> book;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
