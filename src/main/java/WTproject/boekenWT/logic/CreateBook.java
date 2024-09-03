package WTproject.boekenWT.logic;

import WTproject.boekenWT.models.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin
public class CreateBook {

    @Autowired
    private BookRepository bookRepository;

    public void createBook() {

    }
}
