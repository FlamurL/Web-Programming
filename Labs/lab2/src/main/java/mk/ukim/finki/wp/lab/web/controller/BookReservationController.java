package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {

    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping
    public String createReservation(@RequestParam String bookTitle,
                                    @RequestParam int numCopies,
                                    @RequestParam String readerName,
                                    @RequestParam String readerAddress,
                                    HttpServletRequest request,
                                    Model model) {
        String ipAddress = request.getRemoteAddr();

        bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);

        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("readerName", readerName);
        model.addAttribute("ipAddress", ipAddress);
        model.addAttribute("numberOfCopies", numCopies);

        return "reservationConfirmation";
    }
}