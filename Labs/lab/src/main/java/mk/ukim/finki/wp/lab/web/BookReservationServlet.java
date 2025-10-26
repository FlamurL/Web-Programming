package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {

    @Autowired private BookReservationService reservationService;
    @Autowired private SpringTemplateEngine templateEngine;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        int numCopies = Integer.parseInt(req.getParameter("numCopies"));

        BookReservation reservation = reservationService.placeReservation(
                bookTitle, readerName, readerAddress, numCopies);

        req.getSession().setAttribute("reservation", reservation);

        resp.sendRedirect(req.getContextPath() + "/bookReservation");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BookReservation reservation = (BookReservation) req.getSession().getAttribute("reservation");

        if (reservation == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext ctx = new WebContext(exchange);
        ctx.setVariable("reservation", reservation);

        req.getSession().removeAttribute("reservation");

        templateEngine.process("reservationConfirmation.html", ctx, resp.getWriter());
    }
}