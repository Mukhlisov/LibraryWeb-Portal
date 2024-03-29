package webportal.libweb.services;

import java.util.List;
import java.util.Optional;

import webportal.libweb.models.Author;

public interface AuthorService {
    List<Author> findAllAuthors();
    void saveAuthor(Author author);
    Author findByName(String name);
    Optional<Author> findById(Long id);
    void updateAuthor(Author author);
    void deleteById(Long id);
}
