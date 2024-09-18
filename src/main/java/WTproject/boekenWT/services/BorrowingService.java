package WTproject.boekenWT.services;

import WTproject.boekenWT.models.*;
import WTproject.boekenWT.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BorrowingService {

    @Autowired
    BorrowingRepository borrowingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PhysicalBookRepository physicalBookRepository;
    @Autowired
    PhysicalBookCopyRepository physicalBookCopyRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    RequestStatusRepository requestStatusRepository;
    @Autowired
    BorrowingStatusRepository borrowingStatusRepository;
    @Autowired
    AvailabilityRepository availabilityRepository;

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

                    //get a physical bookcopy that is available
                    List<PhysicalBookCopy> availableCopies = physicalBookCopyRepository.findCopiesByAvailabilityType("beschikbaar");

                    if(!availableCopies.isEmpty()) {
                        newBorrowing.setPhysicalBookCopy(availableCopies.getFirst());
                    } else {
                        return "Er zijn geen copies beschikbaar";
                    }

                    borrowingRepository.save(newBorrowing);

                    String updateRequestStatus = updateRequestStatus(requestId);
                    String updateAvailability = updateAvailability(newBorrowing.getPhysicalBookCopy().getCopyId(), 2);

                    return "New borrowing made! & " + updateRequestStatus + " & " + updateAvailability;

                } catch (Exception e) {
                    return "ErrorBS: " + e;
                }
            }
            return "Either UserID or PhysicalBookID was not found";
        }
        return "RequestID not found";
    }

    //read Borrowings by users
    public List<Borrowing> getBorrowings(int userId) {
        List<Borrowing> borrowings = new ArrayList<>();

        if(userRepository.existsById(userId)){
            return borrowingRepository.findBorrowingsByUserId(userId);
        } else {
            return borrowings;
        }
    }

    public String updateRequestStatus(int requestId) {
        Request oldRequest = new Request();

        if (requestRepository.existsById(requestId)) {
            try {
                oldRequest = requestRepository.findById(requestId).get();
                oldRequest.setRequestStatus(requestStatusRepository.findById(2).get());

                requestRepository.save(oldRequest);
                return "The request status has been updated";


            } catch (Exception e) {
                return "ErrorBS: " + e;
            }
        }
        return "request was not found";
    }

    public String updateAvailability(int copyId, int avaialbilityID) {
        PhysicalBookCopy oldCopy = new PhysicalBookCopy();

        if (physicalBookCopyRepository.existsById(copyId)) {
            try {
                oldCopy = physicalBookCopyRepository.findById(copyId).get();
                oldCopy.setAvailability(availabilityRepository.findById(avaialbilityID).get());

                physicalBookCopyRepository.save(oldCopy);
                return "The availability has been updated";


            } catch (Exception e) {
                return "ErrorBS: " + e;
            }
        }
        return "copy was not found";
    }

    //read Borrowing by borrowingid
    public Borrowing getBorrowingInfo(int borrowingId){
//        BorrowingInfoDTO bInfo = null;
        Borrowing borrowing =  new Borrowing();

        if(borrowingRepository.existsById(borrowingId)) {
            borrowing = borrowingRepository.findById(borrowingId).get();
//            bInfo = new BorrowingInfoDTO(borrowing);
        }
        return borrowing;
    }

    public String returnBorrowing(int borrowingId) {

        //check if request exists
        if(borrowingRepository.existsById(borrowingId)) {
            try {
                Borrowing oldBorrowing = borrowingRepository.findById(borrowingId).get();
                oldBorrowing.setReturnDate(new Date());
                oldBorrowing.setBorrowingStatus(borrowingStatusRepository.findById(2).get());

                borrowingRepository.save(oldBorrowing);

                updateAvailability(oldBorrowing.getPhysicalBookCopy().getCopyId(), 1);

            } catch (Exception e) {
                return "ErrorBS: " + e;
            }
        }
        return "Boek is ingeleverd!";
    }
}
