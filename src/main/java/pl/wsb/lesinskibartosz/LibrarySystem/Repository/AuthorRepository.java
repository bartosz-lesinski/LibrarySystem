package pl.wsb.lesinskibartosz.LibrarySystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
