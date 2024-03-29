package webportal.libweb.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import webportal.libweb.models.Issuance;
import webportal.libweb.repository.IssuanceRepo;
import webportal.libweb.services.IssuanceService;

@Service
@AllArgsConstructor
public class IssuanceServiceImpl implements IssuanceService{

    private final IssuanceRepo repository;

    @Override
    public List<Issuance> findAllIssuances() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Optional<Issuance> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Issuance findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public Issuance findByBookId(Long id) {
        return repository.findByBookId(id);
    }

    @SuppressWarnings("null")
    @Override
    public void saveIssuance(Issuance issuance) {
        repository.save(issuance);
    }

    @SuppressWarnings("null")
    @Override
    public void updateIssuance(Issuance issuance) {
        repository.save(issuance);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteIssuanceById(Long id) {
        repository.deleteById(id);
    }

}
