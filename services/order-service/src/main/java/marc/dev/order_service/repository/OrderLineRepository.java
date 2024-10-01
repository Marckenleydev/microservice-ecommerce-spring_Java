package marc.dev.order_service.repository;

import marc.dev.order_service.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findAllByOrderId(Long orderId);
}
