package WTproject.boekenWT.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
public class KnowledgeSource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sourceId;

    @Column
    private Enums.SourceType sourceType;

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
