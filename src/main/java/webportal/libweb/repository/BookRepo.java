package webportal.libweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webportal.libweb.models.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    //TODO custom query
    //Book findByAuthor(String author_name);

}
