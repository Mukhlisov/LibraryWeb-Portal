package webportal.libweb.RentedBook;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentedBookServiceImpl implements RentedBookService{

    private final RentedBookRepo repository;

    @Override
    public List<RentedBook> findAllRentedBooks() {
        return repository.findAll();
    }

    @Override
    public Optional<RentedBook> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public RentedBook findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public RentedBook findByBookId(Long id) {
        return repository.findByBookId(id);
    }

    @Override
    public void saveRentedBook(RentedBook book) {
        repository.save(book);
    }

    @Override
    public void updateRentedBook(RentedBook book) {
        repository.save(book);
    }

    @Override
    public void deleteRentedBookById(Long id) {
        repository.deleteById(id);
    }

}
