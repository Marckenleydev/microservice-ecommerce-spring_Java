package marc.dev.product_service.dtoRequest;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errorResponse
) {
}
