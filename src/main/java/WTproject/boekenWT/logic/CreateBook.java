package WTproject.boekenWT.logic;

import WTproject.boekenWT.models.Author;
import WTproject.boekenWT.models.AuthorRepository;
import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.BookRepository;
import WTproject.boekenWT.models.JsonClass;
import WTproject.boekenWT.models.KnowledgeSource;
import WTproject.boekenWT.models.PhysicalBook;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@SpringBootApplication
@RestController
@CrossOrigin
public class CreateBook {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public void createBook(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/addbook")
    public String addBook(@RequestBody JsonClass json) {
        Book book = new Book();
        Author author = new Author();
        Year year = json.getYear();

        Author newAuthor = json.getAuthor();
        Book newBook = json.getBook();

        author.setAuthorId(newAuthor.getAuthorId());
        author.setName(newAuthor.getName());

        //check if author already exists
        if (authorRepository.existsById(author.getAuthorId())) {
            author = authorRepository.findById(author.getAuthorId()).get();
        }
        else {
            try {
                authorRepository.save(author);
            } catch (Exception e) {
                return "Error: " + e;
            }
        }

        if(bookRepository.existsById(newBook.getIsbn())) {
            return "Book already exists";
        }
        else {
            try {
                book.setIsbn(newBook.getIsbn());
                book.setTitle(newBook.getTitle());
                book.setAuthor(author);
                book.setYear(year);

                bookRepository.save(book);
            } catch (Exception e) {
                return "Error: " + e;
            }
        }

        return "Book added";
    }
    
}
