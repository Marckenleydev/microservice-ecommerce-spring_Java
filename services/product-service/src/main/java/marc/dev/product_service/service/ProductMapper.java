package marc.dev.product_service.service;

import marc.dev.product_service.dtoRequest.ProductPurchaseResponse;
import marc.dev.product_service.dtoRequest.ProductRequest;
import marc.dev.product_service.dtoRequest.ProductResponse;
import marc.dev.product_service.entity.Category;
import marc.dev.product_service.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest){
        return  Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .availableQuantity(productRequest.availableQuantity())
                .category(Category.builder().id(productRequest.categoryId()).build()).build();
    }

    public ProductResponse toProductResponse(Product product){
        return  new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription());
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
