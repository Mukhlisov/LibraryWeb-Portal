package webportal.libweb.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBook();
    List<Book> getRandomBooks();
    Optional<Book> findById(Long id);
    Book saveBook(Book book);
    List<Book> findByTitle(String title);
    Book updateBook(Book book);
    void deleteBook(Long id);
    //Book findByAuthor(String author);
}
