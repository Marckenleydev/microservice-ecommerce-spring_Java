package marc.dev.ecommerce.repository;

import marc.dev.ecommerce.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
