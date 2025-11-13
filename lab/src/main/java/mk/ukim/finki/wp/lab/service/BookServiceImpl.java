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
    public void save(String title, String genre, Double averageRating, Long authorId) {

    }

    @Override
    public void update(Long bookId, String title, String genre, Double averageRating, Long authorId) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Book findById(Long id) {
        return DataHolder.books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }


}
