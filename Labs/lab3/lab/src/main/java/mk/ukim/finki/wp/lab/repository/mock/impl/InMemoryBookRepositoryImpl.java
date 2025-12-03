package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.mock.InMemoryBookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookRepositoryImpl implements InMemoryBookRepository {

    @Override
    public Optional<Book> findById(Long id) {
        return DataHolder.books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(text) &&
                        book.getAverageRating() >= rating)
                .toList();
    }

    @Override
    public Book save(Book book) {
        delete(book.getId());
        DataHolder.books.add(book);
        return book;
    }

    @Override
    public void delete(String name) {
        DataHolder.books.removeIf(b -> b.getTitle().equals(name));
    }

    @Override
    public void delete(Long id) {
        DataHolder.books.removeIf(b -> b.getId().equals(id));
    }
}
