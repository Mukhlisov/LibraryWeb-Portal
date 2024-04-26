package webportal.libweb.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepo extends JpaRepository<Author, Long>{
    Author findByFullName(String fullName);

    @Modifying
    @Query(value = "DELETE FROM authors_books WHERE authors_id = :author_id AND books_id = :book_id", nativeQuery = true)
    int deleteRelationShip(@Param("author_id") Long author_id, @Param("book_id") Long book_id);
}
