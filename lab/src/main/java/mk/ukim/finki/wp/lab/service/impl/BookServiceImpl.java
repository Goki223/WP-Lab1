package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;

import java.util.ArrayList;
import java.util.List;


public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        List<Book> books = DataHolder.books;
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if(book.getTitle().contains(text) && book.getAverageRating() >= rating) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }
}
