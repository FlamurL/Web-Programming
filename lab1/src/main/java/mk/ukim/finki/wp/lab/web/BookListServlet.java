package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListServlet", urlPatterns = {""})
public class BookListServlet extends HttpServlet {

    @Autowired private BookService bookService;
    @Autowired private SpringTemplateEngine templateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String searchText = req.getParameter("searchText");
        Double minRating = null;
        String ratingStr = req.getParameter("minRating");
        if (ratingStr != null && !ratingStr.isBlank()) {
            try { minRating = Double.parseDouble(ratingStr); }
            catch (NumberFormatException ignored) {}
        }

        List<Book> books = (searchText != null && !searchText.isBlank()) || minRating != null
                ? bookService.searchBooks(searchText, minRating)
                : bookService.listAll();

        IWebExchange web_exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext ctx = new WebContext(web_exchange);
        ctx.setVariable("books", books);
        ctx.setVariable("searchText", searchText);
        ctx.setVariable("minRating", minRating);

        templateEngine.process("listBooks.html", ctx, resp.getWriter());
    }
}