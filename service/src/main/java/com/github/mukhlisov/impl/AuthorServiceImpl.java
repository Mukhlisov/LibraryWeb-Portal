package com.github.mukhlisov.impl;

import java.util.List;
import java.util.Optional;

import com.github.mukhlisov.dto.AuthorDto;
import com.github.mukhlisov.Author;
import com.github.mukhlisov.AuthorService;
import com.github.mukhlisov.Book;
import com.github.mukhlisov.repository.AuthorRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo repository;

    @Override
    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Author findByFullName(String fullName) {
        return repository.findByFullName(fullName);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void updateAuthor(Author author) {
       repository.save(author);
    }
    
    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteRelationShips(id);
        repository.deleteById(id);
    }

    @Override
    public Author saveAuthor(AuthorDto authorDto) {
        Author author = new Author(authorDto.getFullName());
        return repository.save(author);
    }

    @Transactional
    @Override
    public void deleteRelationShip(Author author, Book book) {
        repository.deleteRelationShip(author.getId(), book.getId());
    }

    @Override
    public List<Author> findAllAuthors(String phrase) {
        return repository.findByPhrase(phrase);
    }

    @Override
    public Page<Author> findPaginated(int page, int page_size) {
        Pageable pageable = PageRequest.of(page - 1, page_size);
        return repository.findAll(pageable);
    }

    
}
