package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface InMemoryBookRepository {

    Optional<Book> findById(Long id);

    List<Book> findAll();

    List<Book> searchBooks(String text, Double rating);

    Book save(Book book);

    void delete(String name);

    void delete(Long id);
}
