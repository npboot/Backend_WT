package WTproject.boekenWT.controllers;

import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.BookDTO;
import WTproject.boekenWT.models.CatalogDTO;
import WTproject.boekenWT.repositories.AuthorRepository;
import WTproject.boekenWT.repositories.BookRepository;
import WTproject.boekenWT.services.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    BookService bookService;

    @PostMapping("/addBook")    
    public String addBook(@RequestBody BookDTO bookTemplate) {
        try {
            return bookService.addBook(bookTemplate);
        }
        catch (Exception e) {
            return "ErrorBC: " + e;
        }
        
    }

    @GetMapping("/getAllBooks")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getBook")
    public String getBook(@RequestParam int isbn) {

        return bookService.getBook(isbn);
    }

//    @PutMapping("/updateBook")
//    public String updateBook(@RequestBody Book book) {
//        return bookService.updateBookData(book);
//    }
    @PutMapping("/updateBook")
    public String updateBook(@RequestBody BookDTO bookTemplate) {
    return bookService.updateBookData(bookTemplate);
    }

    @DeleteMapping("/deleteBook")
    public String deleteBook(@RequestParam int isbn) {
        return bookService.deleteBookItem(isbn);
    }

    @GetMapping("/catalog")
    public List<CatalogDTO> getCatalogData(){

        return bookService.getAllCatalogData();
    };
}
