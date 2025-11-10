package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.BookReservation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Author> authors = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init() {
        authors.add(new Author("J.K.", "Rowling", "United Kingdom", "British author, best known for Harry Potter series"));
        authors.add(new Author("George", "Orwell", "United Kingdom", "English novelist and essayist"));
        authors.add(new Author("J.R.R.", "Tolkien", "United Kingdom", "English writer and philologist"));

        books.add(new Book("To Kill a Mockingbird", "Fiction", 4.8, authors.get(0)));
        books.add(new Book("1984", "Dystopian", 4.7, authors.get(1)));
        books.add(new Book("Pride and Prejudice", "Romance", 4.6, authors.get(2)));
        books.add(new Book("The Great Gatsby", "Classic", 4.5, authors.get(1)));
        books.add(new Book("Harry Potter", "Fantasy", 4.9, authors.get(2)));
        books.add(new Book("The Hobbit", "Fantasy", 4.7, authors.get(0)));
        books.add(new Book("The Catcher in the Rye", "Fiction", 4.2, authors.get(0)));
        books.add(new Book("Brave New World", "Science Fiction", 4.4, authors.get(2)));
        books.add(new Book("Lord of the Flies", "Adventure", 4.3, authors.get(1)));
        books.add(new Book("Animal Farm", "Satire", 4.6, authors.get(1)));

    }
}