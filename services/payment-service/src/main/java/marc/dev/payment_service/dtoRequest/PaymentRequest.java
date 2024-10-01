package marc.dev.payment_service.dtoRequest;

import marc.dev.payment_service.entity.Customer;
import marc.dev.payment_service.entity.PaymentMethode;

import java.math.BigDecimal;

public record PaymentRequest(
        Long id,
        BigDecimal amount,
        PaymentMethode paymentMethod,
        Long orderId,
        String orderReference,
        Customer customer
) {
}
