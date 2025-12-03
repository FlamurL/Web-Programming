//package mk.ukim.finki.wp.lab.web.servlet;
//
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import mk.ukim.finki.wp.lab.service.BookService;
//
//import java.io.IOException;
//
//@WebServlet(name = "DeleteBookServlet", urlPatterns = "/delete")
//public class DeleteBookServlet extends HttpServlet {
//
//    private final BookService bookService;
//
//    public DeleteBookServlet(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String title = req.getParameter("title");
//        if (title != null) {
//            bookService.delete(title);
//        }
//        resp.sendRedirect("/");
//    }
//}
