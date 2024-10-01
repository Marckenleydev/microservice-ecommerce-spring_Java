package marc.dev.product_service.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import marc.dev.product_service.dtoRequest.ProductPurchaseRequest;
import marc.dev.product_service.dtoRequest.ProductPurchaseResponse;
import marc.dev.product_service.dtoRequest.ProductResponse;
import marc.dev.product_service.dtoRequest.ProductRequest;
import marc.dev.product_service.exception.ProductPurchaseException;
import marc.dev.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Long createProduct(ProductRequest productRequest) {
        var product = mapper.toProduct(productRequest);

        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        var productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or More Products doest not exists");
        }
        var storedRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i=0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchaseProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }

        return purchaseProducts;
    }

    public ProductResponse findById(Long productId) {

        return  repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + productId));
    }

    public List<ProductResponse> findAll() {
        return  repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
