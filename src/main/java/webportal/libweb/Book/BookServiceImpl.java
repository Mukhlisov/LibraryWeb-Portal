package webportal.libweb.Book;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo repository;
    @Override
    public List<Book> findAllBook() {
        return repository.findAll();
    }

    @Override
    public List<Book> getRandomBooks() {
        List<Book> storage = repository.findAll();
        if (storage.size() < 4){
            return storage;
        }
        
        Random random = new Random(System.currentTimeMillis());
        int fromIndex = random.nextInt(storage.size() - 4);
        return storage.subList(fromIndex, fromIndex + 4);
    }

    @Override
    public void saveBook(Book book) {
        repository.save(book);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    /* @Override
    public Book findByAuthor(String author) {
        return repository.findByAuthor(author);
    } */

    @Transactional
    @Override
    public void updateBook(Book book) {
        repository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }
}
