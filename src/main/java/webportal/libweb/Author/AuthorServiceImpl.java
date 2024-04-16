package webportal.libweb.Author;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo repository;

    @Override
    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Author findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void updateAuthor(Author author) {
       repository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void saveAuthor(Author author) {
        repository.save(author);
    }

    
}
