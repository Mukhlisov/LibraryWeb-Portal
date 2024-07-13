package com.github.mukhlisov.impl;

import com.github.mukhlisov.Author;
import com.github.mukhlisov.Book;
import com.github.mukhlisov.BookService;
import com.github.mukhlisov.dto.BookDto;
import com.github.mukhlisov.repository.AuthorRepo;
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

    private static final int LIMIT = 8;
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    @Override
    public List<Book> findAllBook() {
        return bookRepo.findAll();
    }

    @Override
    public List<Book> getRandomBooks() {
        return bookRepo.getRandomBooks(LIMIT);
    }

    @Transactional
    @Override
    public Book saveBook(BookDto bookDto) {
        Book book = new Book(bookDto.getTitle(), bookDto.getQuantity(),
                                bookDto.getYear(), bookDto.getCover());
        book = bookRepo.save(book);
        String[] authors = bookDto.getAuthors().split(";");
        for(String name : authors){
            String fullName = name.trim();

            if (!fullName.isEmpty()){
                Author author = authorRepo.findByFullName(fullName);
                if (author != null){
                    bookRepo.createRelation(book.getId(), author.getId());
                } else {
                    author = authorRepo.save(new Author(fullName));
                    bookRepo.createRelation(book.getId(), author.getId());
                }
            }
        }
        return book;
    }

    @Override
    public List<Book> findByTitle(String phrase) {
        return bookRepo.findByPhrase(phrase);
    }

    @Transactional
    @Override
    public Book updateBook(BookDto bookDto) {
        Book book = new Book(bookDto.getId(), bookDto.getTitle(), bookDto.getQuantity(),
                                bookDto.getYear(), bookDto.getCover());
        book = bookRepo.save(book);
        Set<String> alreadySavedAuthors = bookRepo.findAllAuthorsByBookId(book.getId());
        String[] authors = bookDto.getAuthors().split(";");
        for(String name : authors){
            String fullName = name.trim();

            if (!fullName.isEmpty()){
                if (alreadySavedAuthors.contains(fullName)){
                    alreadySavedAuthors.remove(fullName);
                } else{
                    Author author = authorRepo.findByFullName(fullName);
                    if (author != null){
                        bookRepo.createRelation(book.getId(), author.getId());
                    } else{
                        author = authorRepo.save(new Author(fullName));
                        bookRepo.createRelation(book.getId(), author.getId());
                    }
                }
            }
        }

        for (String authors_to_delete : alreadySavedAuthors) {
            Author author = authorRepo.findByFullName(authors_to_delete);
            bookRepo.deleteRelation(book.getId(), author.getId());
            if (authorRepo.countAuthorRelations(author.getId()) == 0){
                authorRepo.deleteById(author.getId());
            }
        }
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Set<String> alreadySavedAuthors = bookRepo.findAllAuthorsByBookId(id);
        bookRepo.deleteRelations(id);
        for (String name : alreadySavedAuthors){
            String fullName = name.trim();

            if (!fullName.isEmpty()){
                Author author = authorRepo.findByFullName(fullName);
                if (author != null && authorRepo.countAuthorRelations(author.getId()) == 0){
                    authorRepo.deleteById(author.getId());
                }
            }
        }
        bookRepo.deleteById(id);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepo.findById(id);
    }

    @Override
    public Page<Book> findPaginated(int page, int page_size) {
        Pageable pageable = PageRequest.of(page - 1, page_size);
        return bookRepo.findAll(pageable);
    }
}
