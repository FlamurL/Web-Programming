package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import java.util.*;

public interface AuthorRepository {
    List<Author> findAll();
    Optional<Author> findById(Long id);
}
