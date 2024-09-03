package WTproject.boekenWT.logic;

import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin
public class ReadBook {

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/readBook")
    public String getBook(@RequestParam String isbn) {
        System.out.println(isbn);
        if(bookRepository.existsById(isbn)) {
            Book book = bookRepository.findById(isbn).get();
            return book.getTitle() + " by " + book.getAuthor().getName();
        }
        else {
            return "Book not found";
        }
    }
    @GetMapping("/getbooks")
    public List<Book> getBooks() {
        //get all books
        return (List<Book>)bookRepository.findAll();//.iterator().next();
    }


}
