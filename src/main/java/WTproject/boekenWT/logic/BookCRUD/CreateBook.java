package WTproject.boekenWT.logic.BookCRUD;

import WTproject.boekenWT.models.Author;
import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.templates.CreateBookTemplate;
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

    public void createBook(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public String addBook(CreateBookTemplate json) {
        Book book = new Book();
        Author author = new Author();
        Year year = json.year;

        Author newAuthor = json.author;
        Book newBook = json.book;

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
