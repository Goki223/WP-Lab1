package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;

    @PostConstruct
    public void init(){
        books = new ArrayList<>();
        reservations = new ArrayList<>();
        books.add(new Book("Ime", "Romance", 5));
        books.add(new Book("Title", "Romance", 6.9));
        books.add(new Book("LOTR1", "Fantasy", 9.0));
        books.add(new Book("LOTR2", "Fantasy", 9.2));
        books.add(new Book("LOTR3", "Fantasy", 9.5));
        books.add(new Book("Song of Ice and Fire", "Fiction", 5));
        books.add(new Book("Anne Frank", "History", 3));
        books.add(new Book("Mine Kampf", "History", 6.3));
        books.add(new Book("Crime and punishment", "Philosophy", 8.1));
        books.add(new Book("Ime", "Romance", 8));

    }
}
