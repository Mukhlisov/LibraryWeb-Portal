package com.github.mukhlisov;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface BookService {
    List<Book> findAllBook();
    List<Book> getRandomBooks();
    Page<Book> findPaginated(int page, int page_size);
    Optional<Book> findById(Long id);
    Book saveBook(Book book);
    List<Book> findByTitle(String title);
    Book updateBook(Book book);
    void deleteBook(Long id);
    //Book findByAuthor(String author);
}
