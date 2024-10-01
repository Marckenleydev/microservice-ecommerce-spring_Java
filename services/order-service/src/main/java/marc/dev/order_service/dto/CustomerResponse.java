package marc.dev.order_service.dto;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {

}
