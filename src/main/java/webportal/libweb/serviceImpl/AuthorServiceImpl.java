package webportal.libweb.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import webportal.libweb.models.Author;
import webportal.libweb.repository.AuthorRepo;
import webportal.libweb.services.AuthorService;

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

    @SuppressWarnings("null")
    @Override
    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }

    @SuppressWarnings("null")
    @Override
    public void updateAuthor(Author author) {
       repository.save(author);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @SuppressWarnings("null")
    @Override
    public void saveAuthor(Author author) {
        repository.save(author);
    }

    
}
