package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import mk.ukim.finki.wp.lab.model.Author;
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
    public static List<Author> authors = new ArrayList<>();

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
        if(authors.isEmpty()){
            authors.add(new Author(0L, "Dzordz", "RR", "Anglija", "Opis za avtorot"));
            authors.add(new Author(1L, "Ernest", "Hemingvej", "Anglija", "Opis za avtorot"));
            authors.add(new Author(2L, "Franc", "Kafka", "Anglija", "Opis za avtorot"));
        }
        if (books.isEmpty()) {
            books.add(new Book("The Hobbit", "Fantasy", 4.8, authors.get(0)));
            books.add(new Book("1984", "Dystopian", 4.6, authors.get(1)));
            books.add(new Book("To Kill a Mockingbird", "Classic", 4.7, authors.get(2)));
            books.add(new Book("Clean Code", "Programming", 4.5, authors.get(0)));
            books.add(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 4.9, authors.get(1)));
        }


        if (reservations.isEmpty()) {
            reservations = new ArrayList<>();
        }
    }
}
