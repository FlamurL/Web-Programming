package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author findById(Long id);

    List<Author> listAuthors();

    void delete(Long id);

    Author create(
            String name,
            String surname,
            String country,
            String biography
    );

    Author update(
            Long id,
            String name,
            String surname,
            String country,
            String biography
    );
}
