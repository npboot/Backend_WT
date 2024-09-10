package WTproject.boekenWT.models;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class BookDTO {
    Book book;
    Set<Author> authors = new HashSet<Author>();
    Year year;

    public Book getBook() {
        return book;
    }

    public Set<Author> getAuthor() {
        return authors;
    }

    public Year getYear() {
        return year;
    }

    
}
