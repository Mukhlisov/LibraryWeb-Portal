package com.github.mukhlisov;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.github.mukhlisov.dto.AuthorDto;


public interface AuthorService {
    List<Author> findAllAuthors();
    List<Author> findAllAuthors(String phrase);
    Page<Author> findPaginated(int page, int page_size);
    Author saveAuthor(AuthorDto authorDto);
    Author findByFullName(String name);
    Optional<Author> findById(Long id);
    void updateAuthor(Author author);
    void deleteById(Long id);
    void deleteRelationShip(Author author, Book book);
}
