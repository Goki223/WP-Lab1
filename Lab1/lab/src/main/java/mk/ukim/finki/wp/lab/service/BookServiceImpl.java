package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public List<Book> listAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        List<Book> books = DataHolder.books;
        books.removeIf(book -> !book.getTitle().contains(text) || book.getAverageRating() < rating);
        return books;
    }

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }


}
