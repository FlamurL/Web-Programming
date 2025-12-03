package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.repository.mock.InMemoryAuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepositoryImpl implements InMemoryAuthorRepository {

    @Override
    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    @Override
    public void delete(Long id) {
        DataHolder.authors.removeIf(a -> a.getId().equals(id));
    }


    @Override
    public Author save(Author author) {
        delete(author.getId());
        DataHolder.authors.add(author);
        return author;
    }
}
