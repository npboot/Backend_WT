package WTproject.boekenWT.logic;

import WTproject.boekenWT.models.Author;
import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.BookRepository;
import WTproject.boekenWT.models.KnowledgeSource;
import WTproject.boekenWT.models.PhysicalBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@SpringBootApplication
@RestController
@CrossOrigin
public class CreateBook {

    @Autowired
    private BookRepository bookRepository;

    private Author author;
    private Book book;
    private KnowledgeSource source;
    private PhysicalBook physicalBook;

    public void createBook() {
        
    }

    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        String title = "Java for dummies";
        String isbn = "978-90-430-4185-1";
        String authorName = "Barry A. Burd";
        String authorSurname = "Burd";
        String authorId = "1";
        String year = "2019";
        String sourceId = "1";
        String sourceType = "Book";
        String physicalBookId = "1";

        

        bookRepository.save(book);
        return "Book added";
    }
    
}
