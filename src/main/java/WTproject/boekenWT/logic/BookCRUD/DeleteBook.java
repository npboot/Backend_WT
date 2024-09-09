// package WTproject.boekenWT.logic.BookCRUD;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.RestController;

// import WTproject.boekenWT.repositories.BookRepository;

// @SpringBootApplication
// @RestController
// @CrossOrigin
// public class DeleteBook {

//     @Autowired
//     private BookRepository bookRepository;

//     public void deleteBook(BookRepository bookRepository) {
//         this.bookRepository = bookRepository;
//     }

//     public String deleteBookItem(String isbn) {
//         if (bookRepository.existsById(isbn)) {
//             try {
//                 bookRepository.deleteById(isbn);
//                 return "Book deleted";
//             } catch (Exception e) {
//                 return "Error: " + e;
//             }
//         }
//         else {
//             return "Book not found";
//         }
//     }
// }
