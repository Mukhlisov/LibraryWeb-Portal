package webportal.libweb.services;

import webportal.libweb.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAllBook();
    List<Book> getRandomBooks();
    Optional<Book> findById(Long id);
    void saveBook(Book book);
    List<Book> findByTitle(String title);
    void updateBook(Book book);
    void deleteBook(Long id);
    //Book findByAuthor(String author);
}
