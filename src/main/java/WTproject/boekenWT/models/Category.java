package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="CATEGORY")
public class Category {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column
    private String category;

    @ManyToMany
    @JoinTable(
    name = "CATEGORY_BOOK",
    joinColumns = @JoinColumn(name = "categoryId"),
    inverseJoinColumns = @JoinColumn(name = "isbn"))
    private Set<Book> books;


    //getters and setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
