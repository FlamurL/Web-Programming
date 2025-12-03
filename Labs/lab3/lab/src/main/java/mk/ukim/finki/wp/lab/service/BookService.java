package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listBooks();

    Book findById(Long id);

    List<Book> searchBooks(String text, Double rating);

    Book create(
            String title,
            String genre,
            Double averageRating,
            Long authorId
    );

    Book update(
            Long id,
            String title,
            String genre,
            Double averageRating,
            Long authorId
    );

    void delete(Long id);

    List<Book> listBooksByAuthorId(Long authorId);


    List<Book> listBooksByRating(Double rating);

}
