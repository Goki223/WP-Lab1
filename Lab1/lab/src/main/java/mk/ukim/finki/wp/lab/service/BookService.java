package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService extends BookRepository  {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
}
