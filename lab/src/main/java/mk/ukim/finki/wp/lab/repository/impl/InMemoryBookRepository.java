package mk.ukim.finki.wp.lab.repository.impl;

import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;


import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class InMemoryBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
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
