package WTproject.boekenWT.logic.AuthorCRUD;

import WTproject.boekenWT.models.Author;
import WTproject.boekenWT.repositories.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class CreateAuthor {
    @Autowired
    private AuthorRepository authorRepository;

    public void createAuthor(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public String addAuthor(String name, Author author) {
        author.setName(name);

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
        
        return "Author added";
    }
}
