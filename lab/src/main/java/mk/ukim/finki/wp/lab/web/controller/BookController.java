package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // 4.1 — LIST ALL BOOKS
    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        } else {
            model.addAttribute("hasError", false);
        }

        List<Book> books = bookService.listAll();
        model.addAttribute("books", books);
        return "listBooks";
    }

    // 4.2 — ADD NEW BOOK FORM
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "addBook";
    }

    // 4.2 — SAVE NEW BOOK
    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.save(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    // 4.3 — EDIT BOOK FORM
    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        List<Author> authors = authorService.findAll();

        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "addBook"; // reuse same form
    }

    // 4.3 — UPDATE BOOK
    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.update(bookId, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    // 4.4 — DELETE BOOK
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
