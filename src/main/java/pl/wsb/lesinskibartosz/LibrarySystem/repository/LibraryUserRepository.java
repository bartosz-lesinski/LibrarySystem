package pl.wsb.lesinskibartosz.LibrarySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.lesinskibartosz.LibrarySystem.model.LibraryUser;

import java.util.Optional;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {

    Optional<LibraryUser> findByEmail(String email);
}
