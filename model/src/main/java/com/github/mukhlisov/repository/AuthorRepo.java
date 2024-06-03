package com.github.mukhlisov.repository;

import java.util.List;

import com.github.mukhlisov.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepo extends JpaRepository<Author, Long>{

    Author findByFullName(String fullName);

    @Modifying
    @Query(value = "DELETE FROM authors_books WHERE authors_id = :author_id", nativeQuery = true)
    int deleteRelations(@Param("author_id") Long author_id);

    @Query(value = """
            SELECT * FROM authors WHERE Lower(full_name)
            LIKE lower(concat(:phrase, '%')) or Lower(full_name)
            LIKE lower(concat('%', :phrase, '%')) or Lower(full_name)
            LIKE lower(concat('%', :phrase))
            """, nativeQuery = true)
    List<Author> findByPhrase(@Param("phrase") String phrase);

    @Query(value = """
            SELECT COUNT(authors_books.authors_id) FROM authors_books
            WHERE authors_books.authors_id = :author_id
            """, nativeQuery = true)
    int countAuthorRelations(@Param("author_id") Long author_id);
}
