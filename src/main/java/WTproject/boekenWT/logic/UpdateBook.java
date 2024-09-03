package WTproject.boekenWT.logic;

import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@CrossOrigin
public class UpdateBook {

    @Autowired
    private BookRepository bookRepository;

    @PutMapping("/updatebook/{id}")
    public String updateBook(@PathVariable String id, @RequestBody Book book) {
        Book updatedBook = bookRepository.findById(id).get();
        updatedBook.setTitle(book.getTitle());
        updatedBook.setYear(book.getYear());
        updatedBook.setAuthor(book.getAuthor());

        try {
            bookRepository.save(updatedBook);
        } catch (Exception e) {
            return "Error: " + e;
        }
        return "book id " + id + " updated.";
    }
}
