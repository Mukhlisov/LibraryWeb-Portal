package com.github.mukhlisov;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.github.mukhlisov.dto.BookDto;

public interface BookService {
    List<Book> findAllBook();
    List<Book> getRandomBooks();
    Page<Book> findPaginated(int page, int page_size);
    Optional<Book> findById(Long id);
    Book saveBook(BookDto book);
    List<Book> findByTitle(String title);
    Book updateBook(BookDto bookDto);
    Book updateBook(Book book);
    void deleteBook(Long id);
}
