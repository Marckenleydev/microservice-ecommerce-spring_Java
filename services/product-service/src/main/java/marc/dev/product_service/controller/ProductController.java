package marc.dev.product_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import marc.dev.product_service.dtoRequest.ProductPurchaseRequest;
import marc.dev.product_service.dtoRequest.ProductPurchaseResponse;
import marc.dev.product_service.dtoRequest.ProductRequest;
import marc.dev.product_service.dtoRequest.ProductResponse;
import marc.dev.product_service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody @Valid ProductRequest productRequest){
        return  ResponseEntity.ok(productService.createProduct(productRequest));

    }
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody List<ProductPurchaseRequest> request
    ){
        return  ResponseEntity.ok(productService.purchaseProduct(request));
    }

    @GetMapping("{product-id}")
    public  ResponseEntity<ProductResponse> findById(@PathVariable("product-id") Long productId){
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public  ResponseEntity<List<ProductResponse>> findAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }
}
