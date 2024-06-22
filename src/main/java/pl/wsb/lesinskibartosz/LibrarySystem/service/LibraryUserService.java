package pl.wsb.lesinskibartosz.LibrarySystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsb.lesinskibartosz.LibrarySystem.model.LibraryUser;
import pl.wsb.lesinskibartosz.LibrarySystem.repository.LibraryUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryUserService {

    private final LibraryUserRepository libraryUserRepository;


    public LibraryUserService(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }

    public List<LibraryUser> findAll() {
        return libraryUserRepository.findAll();
    }

    public LibraryUser findById(Long id) {
        Optional<LibraryUser> userOptional = libraryUserRepository.findById(id);
        return userOptional.orElse(null);
    }

    public LibraryUser findByEmail(String email) {
        Optional<LibraryUser> userOptional = libraryUserRepository.findByEmail(email);
        return userOptional.orElse(null);
    }

    public LibraryUser save(LibraryUser user) {
        return libraryUserRepository.save(user);
    }

    public boolean deleteById(Long id) {
        if (libraryUserRepository.existsById(id)) {
            libraryUserRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
