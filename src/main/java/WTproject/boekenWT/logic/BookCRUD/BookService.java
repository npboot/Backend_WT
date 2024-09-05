package WTproject.boekenWT.logic.BookCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import WTproject.boekenWT.models.Author;
import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.BookDTO;
import WTproject.boekenWT.repositories.AuthorRepository;
// import WTproject.boekenWT.models.TemplateClasses.CreateBookTemplate;
import WTproject.boekenWT.repositories.BookRepository;

@Component
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    public void saveBook(BookDTO bookTemplate) {
        Book book = bookTemplate.getBook();
        Author author = bookTemplate.getAuthor();   

        bookRepository.save(book);
        authorRepository.save(author);
    }
    
}
