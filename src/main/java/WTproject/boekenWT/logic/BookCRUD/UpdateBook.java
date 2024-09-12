// package WTproject.boekenWT.logic.BookCRUD;

// import WTproject.boekenWT.models.Book;
// import WTproject.boekenWT.repositories.BookRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class UpdateBook {

//     @Autowired
//     private BookRepository bookRepository;

//     public void updateBook(BookRepository bookRepository) {
//         this.bookRepository = bookRepository;
//     }

//     public String updateBookData(Book book) {
//         Book updatedBook = bookRepository.findById(book.getIsbn()).get();
//         updatedBook.setTitle(book.getTitle());
//         updatedBook.setYear(book.getYear());
//         updatedBook.setAuthor(book.getAuthor());

//         try {
//             bookRepository.save(updatedBook);
//         } catch (Exception e) {
//             return "Error: " + e;
//         }
//         return "book id " + book.getIsbn() + " updated.";
//     }
// }
