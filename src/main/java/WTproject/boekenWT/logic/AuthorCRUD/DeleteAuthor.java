package WTproject.boekenWT.logic.AuthorCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import WTproject.boekenWT.repositories.AuthorRepository;

@SpringBootApplication
@RestController
@CrossOrigin
public class DeleteAuthor {

    @Autowired
    private AuthorRepository authorRepository;

    public void deleteBook(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public String deleteBookItem(int id) {
        if (authorRepository.existsById(id)) {
            try {
                authorRepository.deleteById(id);
                return "Author deleted";
            } catch (Exception e) {
                return "Error: " + e;
            }
        }
        else {
            return "Book not found";
        }
    }
}
