//package WTproject.boekenWT.services;
//
//import WTproject.boekenWT.models.*;
//import WTproject.boekenWT.models.DTO.PhysicalBookCopyDTO;
//import WTproject.boekenWT.repositories.PhysicalBookCopyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class PhysicalBookCopyService {
//
//    @Autowired
//    PhysicalBookCopyRepository physicalBookCopyRepository;
//
//    //ADD
//    public String addCopy(PhysicalBookCopyDTO copyTemplate) {
//        PhysicalBookCopy copy = new PhysicalBookCopy();
//
//        PhysicalBookCopy newCopy = copyTemplate.getPhysicalBookCopy();
//
//        Date purchaseDate = copyTemplate.getPurchaseDate();
//        PhysicalCondition condition = copyTemplate.getCondition();
//        Availability availability = copyTemplate.getAvailability();
//
//        if(physicalBookCopyRepository.existsById(newCopy.getCopyId())) {
//            return  "Copy already exists";
//        } else {
//            try {
//                copy.setPhysicalBook(newCopy.getPhysicalBook());
//                copy.setPurchaseDate(purchaseDate);
//                copy.setPhysicalCondition(condition);
//                copy.setAvailability(availability);
//
//                physicalBookCopyRepository.save(copy);
//            } catch (Exception e) {
//                return "ErrorBS: " + e;
//            }
//        }
//
//        return "Copy added";
//    }
//}
