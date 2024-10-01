package marc.dev.order_service.dto;

public record OrderLineRequest(
        Long id,
        Long orderId,
        Long productId,
        double quantity) {
}
