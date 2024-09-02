package WTproject.boekenWT.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="KNOWLEDGESOURCE")
public class KnowledgeSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sourceId;

    @Column
    private Enums.SourceType sourceType;

    @OneToMany(mappedBy = "physicalBookId")
    private Set<PhysicalBook> physicalBooks;

    @Column
    private Enums.Category category;

    @Column
    private float rating;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public Enums.SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(Enums.SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public Enums.Category getCategory() {
        return category;
    }

    public void setCategory(Enums.Category category) {
        this.category = category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
