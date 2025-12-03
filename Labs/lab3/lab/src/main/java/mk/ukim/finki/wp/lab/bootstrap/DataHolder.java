package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// In-memory data holder
@Component
public class DataHolder {

    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;
    public static List<Author> authors = null;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public DataHolder(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // On application startup, initialize the in-memory lists with predefined data
    // On each startup, the lists will be initialized with the same values and the previous values will be lost
    @PostConstruct
    public void init() {
        reservations = new ArrayList<>();

        // Initialize AUTHORS FIRST
        if (authorRepository.findAll().isEmpty()) {
            authors = new ArrayList<>();
            authors.add(new Author(
                    "Gabriel",
                    "Garcia Marquez",
                    "Colombia",
                    "Nobel Prize–winning author known for magical realism, including 'One Hundred Years of Solitude'."
            ));

            authors.add(new Author(
                    "Haruki",
                    "Murakami",
                    "Japan",
                    "Contemporary novelist known for surreal storytelling and works like 'Kafka on the Shore'."
            ));

            authors.add(new Author(
                    "Jane",
                    "Austen",
                    "United Kingdom",
                    "English novelist known for classics such as 'Pride and Prejudice' and 'Sense and Sensibility'."
            ));
            authorRepository.saveAll(authors);
        }

        // THEN initialize books (which reference authors)
        if (bookRepository.findAll().isEmpty()) {
            books = new ArrayList<>();
            books.add(new Book("The Silent Patient", "Thriller", 4.2, authors.get(0)));
            books.add(new Book("Atomic Habits", "Self-Help", 4.8, authors.get(1)));
            books.add(new Book("Dune", "Science Fiction", 4.6, authors.get(2)));
            bookRepository.saveAll(books);
        }
    }
}
