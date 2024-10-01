package marc.dev.order_service.dto;

public record OrderLineResponse(
        Long id,
        double quantity
) {
}
