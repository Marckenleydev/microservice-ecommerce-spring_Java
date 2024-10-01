package marc.dev.product_service.dtoRequest;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "ProductId is mandatory")
        Long productId,
        @NotNull(message = "ProductQuantity is mandatory")
        double quantity
) {
}
