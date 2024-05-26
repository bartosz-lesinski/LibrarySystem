package pl.wsb.lesinskibartosz.LibrarySystem.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Book;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    List<Book> findByPublishYear(int year);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthorId(Long authorId);

    List<Book> findByAvailable(boolean available);


}
