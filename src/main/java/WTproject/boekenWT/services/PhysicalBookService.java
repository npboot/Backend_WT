package WTproject.boekenWT.services;

import WTproject.boekenWT.models.Book;
import WTproject.boekenWT.models.PhysicalBook;
import WTproject.boekenWT.models.PhysicalBookDTO;
import WTproject.boekenWT.repositories.PhysicalBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhysicalBookService {

    @Autowired
    PhysicalBookRepository physicalBookRepository;

    //ADD
    public String addPhysicalBook(PhysicalBookDTO physicalBookTemplate) {
        PhysicalBook physicalBook = new PhysicalBook();
//        Book book = physicalBookTemplate.getBook();

        PhysicalBook newPhysicalBook = physicalBookTemplate.getPhysicalBook();

        if(physicalBookRepository.existsById(newPhysicalBook.getBook().getIsbn())) {
            return  "Physical Book already exists";
        }
        else {
            try {
                physicalBook.setBook(newPhysicalBook.getBook());
                physicalBook.setStock(newPhysicalBook.getStock());
                physicalBook.setArchived(newPhysicalBook.getArchived());

                physicalBookRepository.save(physicalBook);
            } catch (Exception e) {
                return "ErrorBS: " + e;
            }
        }

        return "Physical Book added";
    }
}
