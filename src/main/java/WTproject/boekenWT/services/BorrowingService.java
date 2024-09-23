package WTproject.boekenWT.services;

import WTproject.boekenWT.models.*;
import WTproject.boekenWT.models.DTO.BorrowingInfoDTO;
import WTproject.boekenWT.models.DTO.RequestInfoDTO;
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

    //create new Request with the pBookId and the userId
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

                //get a physical bookcopy that is available
                List<PhysicalBookCopy> availableCopies = physicalBookCopyRepository.findCopiesByAvailabilityType(pBookId, 1);

                // Check whether a copy with the given pBookId is available,
                // if there is one available, convert the request immediately into a borrowing
                if(!availableCopies.isEmpty()) {
                    System.out.println("There are available copies for this book! The request will convert into a borrowing.");
//                    System.out.println("This request has id: " + newRequest.getRequestId());
                    addBorrowing(newRequest.getRequestId());
                } else {
                    System.out.println("Er zijn geen copies beschikbaar. The request will only convert into a borrowing when there is a copy of this book available again.");
                }

            } catch (Exception e) {
                return "ErrorBS: " + e;
            }
        }

        return "New request made!";
    }


    //create new Borrowing from a Request with the requestId
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
                    List<PhysicalBookCopy> availableCopies = physicalBookCopyRepository.findCopiesByAvailabilityType(oldRequest.getPhysicalBook().getPBookId(), 1);

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

    //read Borrowings of a User with the userId
    public List<BorrowingInfoDTO> getBorrowings(int userId) {
        List<BorrowingInfoDTO> borrowingsDTO = new ArrayList<>();

        if(userRepository.existsById(userId)){
            for(Borrowing borrowing:borrowingRepository.findBorrowingsByUserId(userId)) {
                BorrowingInfoDTO borrowingDTO = new BorrowingInfoDTO(borrowing);
                borrowingsDTO.add(borrowingDTO);
            }
            return borrowingsDTO;
        } else {
            return borrowingsDTO;
        }
    }

    //read Requests of a User with the userId
    public List<RequestInfoDTO> getRequests(int userId) {
        List<RequestInfoDTO> requestsDTO = new ArrayList<>();

        if(userRepository.existsById(userId)){
            for(Request request:requestRepository.findRequestsByUserId(userId)) {
                RequestInfoDTO requestDTO = new RequestInfoDTO(request);
                requestsDTO.add(requestDTO);
            }
            return requestsDTO;
        } else {
            return requestsDTO;
        }
    }

    //update RequestStatus of a Request with the requestId
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

    //update the Availability of a PBookCopy with the copyId and availabilityId
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

    //read Borrowing with the borrowingId
    public BorrowingInfoDTO getBorrowingInfo(int borrowingId){
        BorrowingInfoDTO bInfo = null;
        Borrowing borrowing =  new Borrowing();

        if(borrowingRepository.existsById(borrowingId)) {
            borrowing = borrowingRepository.findById(borrowingId).get();
            bInfo = new BorrowingInfoDTO(borrowing);
        }
        return bInfo;
    }

    //read Request with the requestId
    public RequestInfoDTO getRequestInfo(int requestId){
        RequestInfoDTO rInfo = null;
        Request request =  new Request();

        if(requestRepository.existsById(requestId)) {
            request = requestRepository.findById(requestId).get();
            rInfo = new RequestInfoDTO(request);
        }
        return rInfo;
    }

    //update Borrowing and PBookCopy with the borrowingId
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
