package webportal.libweb.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    //TODO custom query
    //Book findByAuthor(String author_name);

}
