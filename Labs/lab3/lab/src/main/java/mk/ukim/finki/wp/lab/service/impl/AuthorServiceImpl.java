package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        this.authorRepository.deleteById(id);
    }

    @Override
    public Author create(String name, String surname, String country, String biography) {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty() ||
                country == null || country.isEmpty() || biography == null || biography.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Author author = new Author(name, surname, country, biography);

        return this.authorRepository.save(author);
    }

    @Override
    public Author update(Long id, String name, String surname, String country, String biography) {

        if (name == null || name.isEmpty() ||
                surname == null || surname.isEmpty() ||
                country == null || country.isEmpty() ||
                biography == null || biography.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        author.setBiography(biography);

        return authorRepository.save(author);
    }
}
