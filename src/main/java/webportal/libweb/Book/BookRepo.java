package webportal.libweb.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM books WHERE Lower(title) LIKE lower(concat(:text, '%')) or Lower(title) LIKE lower(concat('%', :text, '%')) or Lower(title) LIKE lower(concat('%', :text))", nativeQuery = true)
    List<Book> findByTitle(@Param("text") String title);
    
    @Query(value = "SELECT * FROM books ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Book> getRandomBooks(@Param("limit") int limit);
    
    
    //TODO custom query
    //Book findByAuthor(String author_name);

}
