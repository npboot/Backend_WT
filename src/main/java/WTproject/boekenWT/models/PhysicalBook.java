package WTproject.boekenWT.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="PHYSICALBOOK")
public class PhysicalBook {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int pBookId;

    @OneToOne
    @JoinColumn(name = "isbn")
    private Book book;

    @Column
    private int stock;

    @Column
    private boolean archived;

//    @OneToMany(mappedBy = "physicalBook")
//    private Set<PhysicalBookCopy> physicalBookCopies;

    //getters and setters


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean getArchived() {
        return this.archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

}
