package webportal.libweb.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAllAuthors();
    void saveAuthor(Author author);
    Author findByFullName(String name);
    Optional<Author> findById(Long id);
    void updateAuthor(Author author);
    void deleteById(Long id);
}
