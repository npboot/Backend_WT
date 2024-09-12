package WTproject.boekenWT.services;

import WTproject.boekenWT.models.Enums;
import WTproject.boekenWT.models.PhysicalBookCopy;
import WTproject.boekenWT.models.PhysicalBookCopyDTO;
import WTproject.boekenWT.repositories.PhysicalBookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PhysicalBookCopyService {

    @Autowired
    PhysicalBookCopyRepository physicalBookCopyRepository;

    //ADD
    public String addCopy(PhysicalBookCopyDTO copyTemplate) {
        PhysicalBookCopy copy = new PhysicalBookCopy();

        PhysicalBookCopy newCopy = copyTemplate.getPhysicalBookCopy();

        Date purchaseDate = copyTemplate.getPurchaseDate();
        Enums.Condition condition = copyTemplate.getCondition();
        Enums.BorrowingStatus borrowingStatus = copyTemplate.getBorrowingStatus();

        if(physicalBookCopyRepository.existsById(newCopy.getCopyId())) {
            return  "Copy already exists";
        } else {
            try {
                copy.setPhysicalBook(newCopy.getPhysicalBook());
                copy.setPurchaseDate(purchaseDate);
                copy.setPhysicalCondition(condition);
                copy.setStatus(borrowingStatus);

                physicalBookCopyRepository.save(copy);
            } catch (Exception e) {
                return "ErrorBS: " + e;
            }
        }

        return "Copy added";
    }
}
