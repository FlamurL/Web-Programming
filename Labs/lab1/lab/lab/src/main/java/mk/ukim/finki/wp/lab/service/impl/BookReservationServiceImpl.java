package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;

public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository reservationRepository;

    public BookReservationServiceImpl(BookReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        BookReservation reservation = new BookReservation(bookTitle, readerName, readerAddress, (long) numberOfCopies);
        return reservationRepository.save(reservation);
    }
}
