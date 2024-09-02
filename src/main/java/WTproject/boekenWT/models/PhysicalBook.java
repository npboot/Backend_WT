package WTproject.boekenWT.models;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.NVarcharJdbcType;

@Entity
public class PhysicalBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int physicalBookId;

    @OneToMany
    @JoinColumn(name = "sourceId")
    private KnowledgeSource knowledgeSource;

    @OneToOne
    @JoinColumn(name = "isbn")
    private Book book;

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
