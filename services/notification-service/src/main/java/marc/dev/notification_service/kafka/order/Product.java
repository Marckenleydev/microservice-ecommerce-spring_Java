package marc.dev.notification_service.kafka.order;

import java.math.BigDecimal;

public record Product(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
