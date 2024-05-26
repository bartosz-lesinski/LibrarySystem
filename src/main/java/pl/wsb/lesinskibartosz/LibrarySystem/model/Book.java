package pl.wsb.lesinskibartosz.LibrarySystem.model;


import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank
    @Column(name = "book_title", nullable = false, length = 255)
    private String title;

    @NotBlank
    @Positive
    @Column(name = "author_id", nullable = false)
    private int authorId;

    @NotBlank
    @Positive
    @Column(name = "publish_year", nullable = false)
    private int publishYear;

    @NotBlank
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "available")
    private boolean available;

}
