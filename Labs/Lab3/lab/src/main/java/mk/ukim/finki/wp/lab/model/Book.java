package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private double averageRating;

    @ManyToOne
    private Author author;

    public Book(String title, String genre, double averageRating, Author author) {
        this.averageRating = averageRating;
        this.title = title;
        this.genre = genre;
        this.author = author;
    }
}
