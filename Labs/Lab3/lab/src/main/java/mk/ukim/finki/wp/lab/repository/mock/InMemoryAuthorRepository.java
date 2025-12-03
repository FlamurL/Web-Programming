package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface InMemoryAuthorRepository {

    Optional<Author> findById(Long id);

    List<Author> findAll();

    void delete(Long id);

    Author save(Author author);
}
