package webportal.libweb.Author;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import webportal.libweb.Book.Book;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo repository;

    @Override
    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Author findByFullName(String fullName) {
        return repository.findByFullName(fullName);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void updateAuthor(Author author) {
       repository.save(author);
    }
    
    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteRelationShips(id);
        repository.deleteById(id);
    }

    @Override
    public void saveAuthor(Author author) {
        repository.save(author);
    }

    @Transactional
    @Override
    public void deleteRelationShip(Author author, Book book) {
        repository.deleteRelationShip(author.getId(), book.getId());
    }

    
}
