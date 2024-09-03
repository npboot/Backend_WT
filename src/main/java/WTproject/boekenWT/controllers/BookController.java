package WTproject.boekenWT.controllers;

import WTproject.boekenWT.logic.BookCRUD.CreateBook;
import WTproject.boekenWT.logic.BookCRUD.ReadBook;
import WTproject.boekenWT.logic.BookCRUD.UpdateBook;
import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.CreateBookTemplate;
import WTproject.boekenWT.repositories.AuthorRepository;
import WTproject.boekenWT.repositories.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@SpringBootApplication
@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/addBook")    
    public String addBook(@RequestBody CreateBookTemplate bookTemplate) {
        CreateBook createBook = new CreateBook();
        createBook.createBook(bookRepository, authorRepository);

        return createBook.addBook(bookTemplate);
    }

    @GetMapping("/getAllBooks")
    public List<Book> getBooks() {
        ReadBook readBook = new ReadBook();
        readBook.readBook(bookRepository);

        return readBook.getAllBooks();
    }

    @GetMapping("/getBook")
    public String getBook(@RequestParam String isbn) {
        ReadBook readBook = new ReadBook();
        readBook.readBook(bookRepository);

        return readBook.getBook(isbn);
    }

    @PutMapping("/updateBook")
    public String updateBook(@RequestBody Book book) {
        UpdateBook updateBook = new UpdateBook();
        updateBook.updateBook(bookRepository);

        return updateBook.updateBookData(book);
    }
}
