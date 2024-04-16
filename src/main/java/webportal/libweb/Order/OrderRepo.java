package webportal.libweb.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{
    Order findByUserId(Long id);
    Order findByBookId(Long id);
}
