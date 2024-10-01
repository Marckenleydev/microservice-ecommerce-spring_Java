package marc.dev.product_service.repository;

import marc.dev.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByIdInOrderById(List<Long> productIds);
}
