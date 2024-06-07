package pl.wsb.lesinskibartosz.LibrarySystem.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @NotBlank
    @Column(name = "author_name", nullable = false, length = 255)
    private String fullName;
}
