package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    @Getter
    public static List<Book> books = new ArrayList<>();
    @Getter
    public static List<BookReservation> reservations = new ArrayList<>();


    public DataHolder() {
    }

    public static void setBooks(List<Book> books) {
        DataHolder.books = books;
    }

    public static void setReservations(List<BookReservation> reservations) {
        DataHolder.reservations = reservations;
    }
    @PostConstruct
    public void init() {
        if (books.isEmpty()) {
            books.add(new Book("The Hobbit", "Fantasy", 4.8));
            books.add(new Book("1984", "Dystopian", 4.6));
            books.add(new Book("To Kill a Mockingbird", "Classic", 4.7));
            books.add(new Book("Clean Code", "Programming", 4.5));
            books.add(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 4.9));
        }

        if (reservations.isEmpty()) {
            reservations = new ArrayList<>();
        }
    }
}
