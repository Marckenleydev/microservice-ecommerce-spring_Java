package marc.dev.order_service.kafka;

import marc.dev.order_service.dto.CustomerResponse;
import marc.dev.order_service.dto.PurchaseResponse;
import marc.dev.order_service.entity.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
