package WTproject.boekenWT.controllers;

import WTproject.boekenWT.models.DTO.BorrowingInfoDTO;
import WTproject.boekenWT.models.DTO.RequestInfoDTO;
import WTproject.boekenWT.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@CrossOrigin
@RestController
@RequestMapping("/borrowing")
public class BorrowingController {

    @Autowired
    BorrowingService borrowingService;

    @GetMapping("/getInfo")
    public BorrowingInfoDTO getBorrowingInfo(@RequestParam int borrowingId) {
        return borrowingService.getBorrowingInfo(borrowingId);
    }

    @GetMapping("/getBorrowings")
    public List<BorrowingInfoDTO> getBorrowingsInfo(@RequestParam int userId){
        return borrowingService.getBorrowings(userId);
    }

    @GetMapping("/getBorrowingsBook")
    public List<BorrowingInfoDTO> getBorrowingsBookInfo(@RequestParam int pBookId){
        return borrowingService.getBorrowingsPhysicalBook(pBookId);
    }


    @GetMapping("/getRequestInfo")
    public RequestInfoDTO getRequestInfo(@RequestParam int requestId) {
        return borrowingService.getRequestInfo(requestId);
    }

    @GetMapping("/getRequests")
    public List<RequestInfoDTO> getRequestsInfo(@RequestParam int userId){
        return borrowingService.getRequests(userId);
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

    @PostMapping("/addBorrowing")
    public String addBorrowing(@RequestParam int requestId){
        try {

            String addBorrowing = borrowingService.addBorrowing(requestId);

            return addBorrowing;
        }
        catch (Exception e) {
            return "ErrorBC: " + e;
        }
    }

    @PutMapping("/returnBorrowing")
    public String returnBorrowing(@RequestParam int borrowingId) {
        try {
            return borrowingService.returnBorrowing(borrowingId);
        }
        catch (Exception e) {
            return "ErrorBC: " + e;
        }
    }
}
