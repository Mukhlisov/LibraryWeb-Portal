package webportal.libweb.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
    
    @Query(value = "SELECT * FROM books ORDER BY RANDOM() LIMIT COALESCE((SELECT COUNT(*) FROM books), 4)", nativeQuery = true)
    List<Book> getRandomBooks(@Param("limit") int limit);
    //TODO custom query
    //Book findByAuthor(String author_name);

}
