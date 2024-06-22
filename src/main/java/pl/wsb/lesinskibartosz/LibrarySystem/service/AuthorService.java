package pl.wsb.lesinskibartosz.LibrarySystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Author;
import pl.wsb.lesinskibartosz.LibrarySystem.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {


    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> findByFullName(String fullName) {
        return authorRepository.findByFullName(fullName);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

    public List<Author> addAuthors(List<Author> authors) {
        List<Author> addedAuthors = new ArrayList<>();
        for (Author author : authors) {
            Optional<Author> existingAuthorOptional = findByFullName(author.getFullName());
            if (existingAuthorOptional.isEmpty()) {
                Author addedAuthor = save(author);
                addedAuthors.add(addedAuthor);
            }
        }
        return addedAuthors;
    }
}