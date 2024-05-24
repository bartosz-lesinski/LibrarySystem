package pl.wsb.lesinskibartosz.LibrarySystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.lesinskibartosz.LibrarySystem.model.LibraryUser;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Integer> {
}
