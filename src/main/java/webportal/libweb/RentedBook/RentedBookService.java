package webportal.libweb.RentedBook;

import java.util.List;
import java.util.Optional;

public interface RentedBookService {
    List<RentedBook> findAllRentedBooks();
    Optional<RentedBook> findById(Long id);
    RentedBook findByUserId(Long id);
    RentedBook findByBookId(Long id);
    void saveRentedBook(RentedBook book);
    void updateRentedBook(RentedBook book);
    void deleteRentedBookById(Long id);

}
