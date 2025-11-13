package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = {"/bookReservation"})
public class BookReservationServlet extends HttpServlet {

    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine templateEngine;

    // Constructor injection
    public BookReservationServlet(BookReservationService bookReservationService,
                                  SpringTemplateEngine templateEngine) {
        this.bookReservationService = bookReservationService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        // Read parameters from the form
        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        String numberOfCopiesStr = req.getParameter("numberOfCopies");

        // Validate input
        if (bookTitle == null || bookTitle.isEmpty() ||
                readerName == null || readerName.isEmpty() ||
                readerAddress == null || readerAddress.isEmpty() ||
                numberOfCopiesStr == null || numberOfCopiesStr.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing reservation data");
            return;
        }

        try {
            int numberOfCopies = Integer.parseInt(numberOfCopiesStr);
            String clientIp = req.getRemoteAddr();

            // Place reservation
            BookReservation reservation = bookReservationService.placeReservation(
                    bookTitle, readerName, readerAddress, numberOfCopies
            );

            // Prepare Thymeleaf context
            IWebExchange webExchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req, resp);

            WebContext context = new WebContext(webExchange);
            context.setVariable("reservation", reservation);
            context.setVariable("clientIp", clientIp);

            // Render confirmation page
            templateEngine.process("reservationConfirmation.html", context, resp.getWriter());

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Number of copies must be a valid number");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing reservation");
        }
    }
}
