package WTproject.boekenWT.services;

import WTproject.boekenWT.models.Borrowing;
import WTproject.boekenWT.models.BorrowingInfoDTO;
import WTproject.boekenWT.models.PhysicalBook;
import WTproject.boekenWT.models.Request;
import WTproject.boekenWT.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class BorrowingService {

    @Autowired
    BorrowingRepository borrowingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PhysicalBookRepository physicalBookRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    RequestStatusRepository requestStatusRepository;
    @Autowired
    BorrowingStatusRepository borrowingStatusRepository;

    //create new Request
    public String addRequest(int pBookId, int userId) {

        Request newRequest = new Request();

        //check if user exists
        if(userRepository.existsById(userId) && physicalBookRepository.existsById(pBookId)){
            try {
                newRequest.setUser(userRepository.findById(userId).get());
                newRequest.setPhysicalBook(physicalBookRepository.findById(pBookId).get());
                //Nog even op terugkomen, huidige tijd loopt nu 2u achter
                newRequest.setRequestDate(new Timestamp(System.currentTimeMillis()));
                newRequest.setRequestStatus(requestStatusRepository.findById(1).get());

                requestRepository.save(newRequest);
            } catch (Exception e) {
                return "ErrorBS: " + e;
            }
        }

        return "New request made!";
    }

    //create new Borrowing
    public String addBorrowing(int requestId) {

        Borrowing newBorrowing = new Borrowing();

        //check if request exists
        if(requestRepository.existsById(requestId)) {
            Request oldRequest = requestRepository.findById(requestId).get();

            if(userRepository.existsById(oldRequest.getUser().getUserId()) && physicalBookRepository.existsById(oldRequest.getPhysicalBook().getPBookId())){
                try {
                    newBorrowing.setRequest(oldRequest);
                    newBorrowing.setStartDate(new Date());
                    newBorrowing.setBorrowingStatus(borrowingStatusRepository.findById(1).get());

                    //
//                    newBorrowing.setPhysicalBookCopy(physicalBookRepository.findById());

//                    requestRepository.save(newBorrowing);
                } catch (Exception e) {
                    return "ErrorBS: " + e;
                }
            }
        }



        return "New borrowing made!";
    }

    //read Borrowings by users

    //read Borrowing by borrowingid
    public Borrowing getBorrowingInfo(int borrowingId){
        BorrowingInfoDTO bInfo = null;
        Borrowing borrowing =  new Borrowing();

        if(borrowingRepository.existsById(borrowingId)) {
            borrowing = borrowingRepository.findById(borrowingId).get();
            bInfo = new BorrowingInfoDTO(borrowing);
        }
        return borrowing;
    }
}
