package pl.wsb.lesinskibartosz.LibrarySystem.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Book;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
