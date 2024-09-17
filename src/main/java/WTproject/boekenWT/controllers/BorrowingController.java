package WTproject.boekenWT.controllers;

import WTproject.boekenWT.models.Borrowing;
import WTproject.boekenWT.models.BorrowingInfoDTO;
import WTproject.boekenWT.services.BookService;
import WTproject.boekenWT.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@CrossOrigin
@RestController
@RequestMapping("/borrowing")
public class BorrowingController {

    @Autowired
    BorrowingService borrowingService;

    @GetMapping("/getInfo")
    public Borrowing getBorrowingInfo(@RequestParam int borrowingId) {
//        return borrowingService.getBorrowingInfo(borrowingId);
//        System.out.println(borrowingService.getBorrowingInfo(borrowingId).getTitle());
//        System.out.println(borrowingService.getBorrowingInfo(borrowingId).getBorrowingDate());
//        System.out.println(borrowingService.getBorrowingInfo(borrowingId).getIsbn());
//        System.out.println(borrowingService.getBorrowingInfo(borrowingId).getAuthorName());
//        System.out.println(borrowingService.getBorrowingInfo(borrowingId).getReturnDate());
//        System.out.println(borrowingService.getBorrowingInfo(borrowingId).getCopyID());
        return borrowingService.getBorrowingInfo(borrowingId);
    }

    @PostMapping("/addRequest")
    public String addRequest(@RequestParam int pBookId, @RequestParam int userId){
        try {
            return borrowingService.addRequest(pBookId, userId);
        }
        catch (Exception e) {
            return "ErrorBC: " + e;
        }

    }

//    PostMapping("/addBorrowing")
//    public String addBorrowing(@RequestBody NewBorrowingDTO)

}