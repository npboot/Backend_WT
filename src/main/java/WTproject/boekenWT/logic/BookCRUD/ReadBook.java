// package WTproject.boekenWT.logic.BookCRUD;

// import WTproject.boekenWT.models.Book;
// import WTproject.boekenWT.repositories.BookRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import java.util.List;

// @Component
// public class ReadBook {

//     @Autowired
//     private BookRepository bookRepository;

//     public void readBook(BookRepository bookRepository) {
//         this.bookRepository = bookRepository;
//     }

//     public String getBook(String isbn) {
//         if(bookRepository.existsById(isbn)) {
//             Book book = bookRepository.findById(isbn).get();
//             return book.getTitle() + " by " + book.getAuthor().getName();
//         }
//         else {
//             return "Book not found";
//         }
//     }

//     public List<Book> getAllBooks() {
//         //get all books
//         return (List<Book>)bookRepository.findAll();
//     }


// }
