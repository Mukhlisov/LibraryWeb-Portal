package webportal.libweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webportal.libweb.models.RentedBook;

@Repository
public interface RentedBookRepo extends JpaRepository<RentedBook, Long>{
    RentedBook findByUserId(Long id);
    RentedBook findByBookId(Long id);
}
