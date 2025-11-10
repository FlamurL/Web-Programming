package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import java.util.*;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    Optional<Book> findById(Long id);
    Book save(String title, String genre, Double averageRating, Author author);
    void deleteById(Long id);
}

