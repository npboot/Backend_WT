package WTproject.boekenWT.services;

import java.time.Year;
import java.util.HashSet;
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

    //ADD
    public String addBook(BookDTO bookTemplate) {

        Book book = new Book();
        Set<Author> authors = new HashSet<Author>();
        Year year = bookTemplate.getYear();

        Set<Author> newAuthors = bookTemplate.getAuthor();
        Book newBook = bookTemplate.getBook();


        // Check if the author already exists
        for(Author newAuthor: newAuthors) {
            Author author = new Author();
            System.out.println("First check: " + newAuthor.getName() + "  " + newAuthor.getAuthorId());
            if (authorRepository.existsById(newAuthor.getAuthorId())) {
                System.out.println("Author bestaat al");
                // Fetch the existing author from the database
                author = authorRepository.findById(newAuthor.getAuthorId()).get();
                System.out.println("Nieuwe author " + author.getName() + " is toegevoegd");
                authors.add(author);
            } else {
                // Create and save the new author
                author.setName(newAuthor.getName());
                System.out.println("Author is nieuw");
                try {
                    author = authorRepository.save(author);
                    authors.add(author);
                    System.out.println("Nieuwe author " + author.getName() + " is toegevoegd");

                    // Save and get the managed entity
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            }
        }


        if(bookRepository.existsById(newBook.getIsbn())) {
            return "Book already exists";
        }
        else {
            try {
                book.setIsbn(newBook.getIsbn());
                book.setTitle(newBook.getTitle());
                System.out.println("authors.size: " + authors.size());
                for(Author newAuthor: authors) {
                    System.out.println("Second check: " + newAuthor.getName());
                    book.addAuthor(newAuthor);
                }
                book.setYear(year);
                book.setIsOnline(newBook.getIsOnline());
                book.setIsPhysical(newBook.getIsPhysical());
                book.setSummary(newBook.getSummary());
                // book.setCategories(newBook.getCategories());

                bookRepository.save(book);
            } catch (Exception e) {
                return "ErrorBS: " + e;
            }
        }

        return "Book added";
    }

    //GET
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

    //UPDATE
    public String updateBookData(Book book) {
        System.out.println(book.getIsbn());
        Book updatedBook = bookRepository.findById(book.getIsbn()).get();
        updatedBook.setTitle(book.getTitle());
        updatedBook.setYear(book.getYear());
        
        // if (book.getAuthors().size() > 0) {
        //     for (int i = 0; i < book.getAuthors().size(); i++) {
        //         //if author doesn't exist, add it
        //         if (updatedBook.getAuthors().iterator().next().getAuthorId() != book.getAuthors().iterator().next().getAuthorId()) {
        //             updatedBook.addAuthor(book.getAuthors().iterator().next());
        //         }
        //     }
        // }
        Object[] authorArray = book.getAuthors().toArray();

        for(int i=0; i < authorArray.length; i++) {
            Author author = (Author) authorArray[i];
            System.out.println("SECOND TEST:" + author);
        }

        try {
            bookRepository.save(updatedBook);
        } catch (Exception e) {
            return "Error: " + e;
        }
        return "book id " + book.getIsbn() + " updated.";
    }

    //DELETE
    public String deleteBookItem(int isbn) {
        if (bookRepository.existsById(isbn)) {
            try {
                bookRepository.deleteById(isbn);
                return "Book deleted";
            } catch (Exception e) {
                return "Error: " + e;
            }
        }
        else {
            return "Book not found";
        }
    }
}
