package marc.dev.order_service.dto;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errorResponse
) {
}
