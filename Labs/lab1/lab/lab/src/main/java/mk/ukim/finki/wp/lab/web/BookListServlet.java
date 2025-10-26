@WebServlet(name = "BookListServlet", urlPatterns = "/")
public class BookListServlet extends HttpServlet {

    private final BookService bookService = new BookServiceImpl(new InMemoryBookRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.listAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/listBooks.html").forward(req, resp);
    }
}