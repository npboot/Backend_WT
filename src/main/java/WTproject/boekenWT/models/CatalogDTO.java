package WTproject.boekenWT.models;

public class CatalogDTO {
    private String title;
    private String author;
    private int year;
    private int available;
    private int total;
    private int isbn;

    public CatalogDTO(Book book) {
        title = book.getTitle();
        author = "";
        for(Author a:book.getAuthors()) {
            author+= a.getName()+", ";
        };
        year = book.getYear().getValue();
        total = 5;
        available = 3;
        isbn = book.getIsbn();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
}
