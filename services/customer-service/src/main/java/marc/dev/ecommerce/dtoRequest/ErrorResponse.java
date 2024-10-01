package marc.dev.ecommerce.dtoRequest;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errorResponse
) {
}
