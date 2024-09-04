package WTproject.boekenWT.logic.BookCRUD;

import WTproject.boekenWT.logic.AuthorCRUD.CreateAuthor;
import WTproject.boekenWT.models.Author;
import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.TemplateClasses.CreateBookTemplate;
import WTproject.boekenWT.repositories.AuthorRepository;
import WTproject.boekenWT.repositories.BookRepository;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class CreateBook extends CreateBookTemplate{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    private CreateAuthor createAuthor;

    public void createBook(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;

        this.createAuthor = new CreateAuthor();
        this.createAuthor.createAuthor(authorRepository);
    }

    public String addBook(CreateBookTemplate json) {
        Book book = new Book();
        Author author = new Author();
        Year year = json.year;

        Author newAuthor = json.author;
        Book newBook = json.book;
        
        //check if author already exists, if not, add it
        createAuthor.addAuthor(newAuthor.getName(), author);

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
