package com.github.mukhlisov.impl;

import com.github.mukhlisov.Book;
import com.github.mukhlisov.BookService;
import com.github.mukhlisov.dto.BookDto;
import com.github.mukhlisov.repository.BookRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Transactional
    @Override
    public Book saveBook(BookDto bookDto) {
        Book book = new Book(bookDto.getTitle(), bookDto.getQuantity(),
                                bookDto.getYear(), bookDto.getCover());
        book = repository.save(book);
        String[] authors = bookDto.getAuthors().split(";");
        for(String name : authors){
            String fullName = name.trim();
            if (fullName.isEmpty()){
                continue;
            }

            Long author_id = repository.findAuthorByFullName(fullName);
            if (author_id != null){
                repository.createRelationShip(book.getId(), author_id);
            } else{
                repository.insertAuthor(fullName);
                author_id = repository.findAuthorByFullName(fullName);
                repository.createRelationShip(book.getId(), author_id);
            }
        }
        return book;
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
    public Book updateBook(BookDto bookDto) {
        Book book = new Book(bookDto.getId(), bookDto.getTitle(), bookDto.getQuantity(),
                                bookDto.getYear(), bookDto.getCover());
        book = repository.save(book);
        Set<String> alreadySavedAuthors = repository.findAllAuthorsByBookId(book.getId());
        String[] authors = bookDto.getAuthors().split(";");
        for(String name : authors){
            String fullName = name.trim();
            if (fullName.isEmpty()){
                continue;
            }

            if (alreadySavedAuthors.contains(fullName)){
                alreadySavedAuthors.remove(fullName);
            } else{
                Long author_id = repository.findAuthorByFullName(fullName);
                if (author_id != null){
                    repository.createRelationShip(book.getId(), author_id);
                } else{
                    repository.insertAuthor(fullName);
                    author_id = repository.findAuthorByFullName(fullName);
                    repository.createRelationShip(book.getId(), author_id);
                }
            }
        }
        for (String authors_to_delete : alreadySavedAuthors) {
            Long author_id = repository.findAuthorByFullName(authors_to_delete);
            repository.deleteRelationShip(book.getId(), author_id);
        }
        return book;
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
