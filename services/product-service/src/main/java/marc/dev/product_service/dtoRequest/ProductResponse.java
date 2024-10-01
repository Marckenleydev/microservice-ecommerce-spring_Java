package marc.dev.product_service.dtoRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Long categoryId,
        String categoryName,
        String categoryDescription
) {
}
