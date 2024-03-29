package webportal.libweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webportal.libweb.models.Author;


@Repository
public interface AuthorRepo extends JpaRepository<Author, Long>{
    Author findByName(String name);
}
