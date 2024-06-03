package com.github.mukhlisov.repository;

import java.util.List;
import java.util.Set;

import com.github.mukhlisov.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM books WHERE Lower(title) LIKE lower(concat(:phrase, '%')) or Lower(title) LIKE lower(concat('%', :phrase, '%')) or Lower(title) LIKE lower(concat('%', :phrase))", nativeQuery = true)
    List<Book> findByPhrase(@Param("phrase") String phrase);
    
    @Query(value = "SELECT * FROM books ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Book> getRandomBooks(@Param("limit") int limit);

    @Modifying
    @Query(value = "INSERT INTO authors_books (authors_id, books_id) SELECT :author_id, :book_id WHERE NOT EXISTS (SELECT 1 FROM authors_books WHERE authors_id = :author_id AND books_id = :book_id);", nativeQuery = true)
    void createRelation(@Param("book_id") Long book_id, @Param("author_id") Long author_id);

    @Modifying
    @Query(value = "DELETE FROM authors_books WHERE books_id = :book_id", nativeQuery = true)
    int deleteRelations(@Param("book_id") Long id);
    
    @Modifying
    @Query(value = "DELETE FROM authors_books WHERE books_id = :book_id AND authors_id = :author_id", nativeQuery = true)
    int deleteRelation(@Param("book_id") Long book_id, @Param("author_id") Long author_id);

    @Query(value = "SELECT full_name FROM authors, authors_books WHERE authors_books.books_id = :book_id AND authors_books.authors_id = id", nativeQuery = true)
    Set<String> findAllAuthorsByBookId(@Param("book_id") Long book_id);

    @Modifying
    @Query(value = "UPDATE books SET quantity = quantity + 1 WHERE books.id = :book_id", nativeQuery = true)
    int incrementQuantityById(@Param("book_id") Long book_id);
}
