package com.github.mukhlisov;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllIssuances();
    Optional<Order> findById(Long id);
    Order findByUserId(Long id);
    Order findByBookId(Long id);
    void saveIssuance(Order issuance);
    void updateIssuance(Order issuance);
    void deleteIssuanceById(Long id);

}
