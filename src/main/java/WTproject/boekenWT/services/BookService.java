package WTproject.boekenWT.services;

import java.time.Year;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import WTproject.boekenWT.models.Author;
import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.BookDTO;
import WTproject.boekenWT.repositories.AuthorRepository;
import WTproject.boekenWT.repositories.BookRepository;

@Component
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookDTO bookTemplate) {
        Book book = new Book();
        Author author = new Author();
        Year year = bookTemplate.getYear();

        Author newAuthor = bookTemplate.getAuthor();
        Book newBook = bookTemplate.getBook();

        // Check if the author already exists
        if (authorRepository.existsById(newAuthor.getAuthorId())) {
            // Fetch the existing author from the database
            author = authorRepository.findById(newAuthor.getAuthorId()).get();
        } else {
            // Create and save the new author
            author.setName(newAuthor.getName());
            try {
                author = authorRepository.save(author); // Save and get the managed entity
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        }

        if(bookRepository.existsById(newBook.getIsbn())) {
            return "Book already exists";
        }
        else {
            try {
                book.setIsbn(newBook.getIsbn());
                book.setTitle(newBook.getTitle());
                book.addAuthor(author);
                book.setYear(year);

                bookRepository.save(book);
            } catch (Exception e) {
                return "ErrorBS: " + e;
            }
        }

        return "Book added";
    }
    public String getBook(int isbn) {
        if(bookRepository.existsById(isbn)) {
            Book book = bookRepository.findById(isbn).get();
            return book.getTitle() + " by " + book.getAuthors().iterator().next().getName();
        }
        else {
            return "Book not found";
        }
    }

    public List<Book> getAllBooks() {
        
        return (List<Book>)bookRepository.findAll();
    }
}
