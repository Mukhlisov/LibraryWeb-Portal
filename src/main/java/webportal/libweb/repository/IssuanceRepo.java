package webportal.libweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webportal.libweb.models.Issuance;

@Repository
public interface IssuanceRepo extends JpaRepository<Issuance, Long>{
    Issuance findByUserId(Long id);
    Issuance findByBookId(Long id);
}
