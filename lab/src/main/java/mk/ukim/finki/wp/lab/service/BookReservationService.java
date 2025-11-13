package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Service;


public interface BookReservationService extends BookReservationRepository {
    BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies);
}