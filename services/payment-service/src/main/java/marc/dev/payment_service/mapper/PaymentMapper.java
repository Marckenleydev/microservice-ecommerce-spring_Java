package marc.dev.payment_service.mapper;

import marc.dev.payment_service.dtoRequest.PaymentRequest;
import marc.dev.payment_service.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest paymentRequest) {
        return  Payment.builder()
                .id(paymentRequest.id())
                .orderId(paymentRequest.orderId())
                .paymentMethod(paymentRequest.paymentMethod())
                .amount(paymentRequest.amount())
                .build();
    }
}
