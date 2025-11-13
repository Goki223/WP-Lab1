package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookListServlet", urlPatterns = {"/listbooks"})
public class BookListServlet extends HttpServlet {

    private final BookService bookService;
    private final SpringTemplateEngine templateEngine;

    // Constructor injection
    public BookListServlet(BookService bookService, SpringTemplateEngine templateEngine) {
        this.bookService = bookService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        try {
            // Read search parameters
            String searchText = req.getParameter("searchText");
            String minRatingStr = req.getParameter("minRating");
            double minRating = 0;

            if (minRatingStr != null && !minRatingStr.isEmpty()) {
                try {
                    minRating = Double.parseDouble(minRatingStr);
                } catch (NumberFormatException e) {
                    minRating = 0; // fallback if invalid
                }
            }

            // Get all books and filter them
            var allBooks = bookService.listAll();
            double finalMinRating = minRating; // effectively final
            var filteredBooks = allBooks.stream()
                    .filter(b -> (searchText == null || searchText.isEmpty()
                            || b.getTitle().toLowerCase().contains(searchText.toLowerCase()))
                            && b.getAverageRating() >= finalMinRating)
                    .toList();

            // Build Thymeleaf WebContext
            IWebExchange webExchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req, resp);

            WebContext context = new WebContext(webExchange);

            // Pass filtered list and current search parameters to the template
            context.setVariable("books", filteredBooks);
            context.setVariable("searchText", searchText == null ? "" : searchText);
            context.setVariable("minRating", minRatingStr == null ? "" : minRatingStr);

            // Render template
            templateEngine.process("listBooks.html", context, resp.getWriter());

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching book list");
        }
    }



}
