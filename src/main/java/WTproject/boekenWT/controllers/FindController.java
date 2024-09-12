package WTproject.boekenWT.controllers;

import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@CrossOrigin
@RestController
@RequestMapping("/search")
public class FindController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/findisbn/{nummer}")
    public String findIsbn(@PathVariable int nummer) {
            List<Book> books = bookRepository.findBookByIsbn(nummer);
            for (Book B: books){

                System.out.println(B.getTitle());
            }
            return "found!";
    }

}
