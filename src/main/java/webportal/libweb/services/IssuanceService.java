package webportal.libweb.services;

import java.util.List;
import java.util.Optional;

import webportal.libweb.models.Issuance;

public interface IssuanceService {
    List<Issuance> findAllIssuances();
    Optional<Issuance> findById(Long id);
    Issuance findByUserId(Long id);
    Issuance findByBookId(Long id);
    void saveIssuance(Issuance issuance);
    void updateIssuance(Issuance issuance);
    void deleteIssuanceById(Long id);

}
