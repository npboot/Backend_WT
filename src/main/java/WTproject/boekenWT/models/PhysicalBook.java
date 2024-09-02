package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="PHYSICALBOOK")
public class PhysicalBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int physicalBookId;

    @ManyToOne
    @JoinColumn(name = "sourceId")
    private KnowledgeSource knowledgeSource;

    @OneToOne
    @JoinColumn(name = "isbn")
    private Book book;

    @OneToMany(mappedBy = "physicalBookCopyId")
    private Set<PhysicalBookCopy> physicalBookCopies;

    @Column
    private int stock;

    public int getPhysicalBookId() {
        return physicalBookId;
    }

    public void setPhysicalBookId(int physicalBookId) {
        this.physicalBookId = physicalBookId;
    }

    public KnowledgeSource getKnowledgeSource() {
        return knowledgeSource;
    }

    public void setKnowledgeSource(KnowledgeSource knowledgeSource) {
        this.knowledgeSource = knowledgeSource;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
