package webportal.libweb.Book;

import java.util.List;

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

    @Query(value = "DELETE FROM authors_books WHERE books_id = :book_id", nativeQuery = true)
    @Modifying
    int deleteRelationShips(@Param("book_id") Long id);
    
    
    //TODO custom query
    //Book findByAuthor(String author_name);

}
