package pl.wsb.lesinskibartosz.LibrarySystem.model;


import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "book_title", nullable = false, length = 255)
    private String title;

    @NotNull
    @Positive
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @NotNull
    @Positive
    @Column(name = "publish_year", nullable = false)
    private int publishYear;

    @NotBlank
    @Column(name = "isbn", nullable = false, length = 25)
    private String isbn;

    @Column(name = "available", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean available;

}
