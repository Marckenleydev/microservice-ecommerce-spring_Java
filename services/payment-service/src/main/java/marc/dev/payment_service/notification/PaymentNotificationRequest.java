package marc.dev.payment_service.notification;

import marc.dev.payment_service.entity.PaymentMethode;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethode paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
