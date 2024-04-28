package webportal.libweb.Book;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private static final int LIMIT = 4;
    private final BookRepo repository;
    @Override
    public List<Book> findAllBook() {
        return repository.findAll();
    }

    @Override
    public List<Book> getRandomBooks() {
        return repository.getRandomBooks(LIMIT);
    }

    @Override
    public Book saveBook(Book book) {
        return repository.save(book);
    }

    @Override
    public List<Book> findByTitle(String phrase) {
        return repository.findByPhrase(phrase);
    }

    /* @Override
    public Book findByAuthor(String author) {
        return repository.findByAuthor(author);
    } */

    @Transactional
    @Override
    public Book updateBook(Book book) {
        return repository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        repository.deleteRelationShips(id);
        repository.deleteById(id);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Book> findPaginated(int page, int page_size) {
        Pageable pageable = PageRequest.of(page - 1, page_size);
        return repository.findAll(pageable);
    }
}
