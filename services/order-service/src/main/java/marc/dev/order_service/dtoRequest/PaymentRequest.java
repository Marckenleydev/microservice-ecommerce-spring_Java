package marc.dev.order_service.dtoRequest;

import marc.dev.order_service.dto.CustomerResponse;
import marc.dev.order_service.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}
