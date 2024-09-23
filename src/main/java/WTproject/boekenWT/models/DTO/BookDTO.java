package WTproject.boekenWT.models.DTO;

import WTproject.boekenWT.models.*;

import java.time.Year;
import java.util.*;

public class BookDTO {
    Book book;
    Set<Author> authors = new HashSet<Author>();
    Set<Category> categories = new HashSet<Category>();
    Year year;
    int amount;


    List<Availability> availability = new ArrayList<>();
    List<PhysicalCondition> physicalCondition = new ArrayList<>();
    List<Date> purchaseDate = new ArrayList<>();

    public Book getBook() {
        return book;
    }

    public Set<Author> getAuthor() {
        return authors;
    }
    public Set<Category> getCategories() {
        return categories;
    }

    public Year getYear() {
        return year;
    }

    public int getAmount() {
        return amount;
    }


    public List<Availability> getAvailability() {
        return availability;
    }

    public List<PhysicalCondition> getPhysicalCondition() {
        return physicalCondition;
    }

    public List<Date> getPurchaseDate() {
        return purchaseDate;
    }
}
