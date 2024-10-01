package marc.dev.product_service.dtoRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
         Long id,
         @NotNull(message = "Product name is required")
         String name,
         @NotNull(message = "Product description is required")
         String description,
         @Positive(message = "Product availableQuantity is must be greater than 0")
         double availableQuantity,
         @Positive(message = "Product price is must be greater than 0")
         BigDecimal price,
         @NotNull(message = "Product categoryId is required")
         Long categoryId
) {
}
