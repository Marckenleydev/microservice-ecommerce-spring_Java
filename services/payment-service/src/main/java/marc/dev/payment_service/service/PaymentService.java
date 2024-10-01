package marc.dev.payment_service.service;

import lombok.RequiredArgsConstructor;
import marc.dev.payment_service.notification.PaymentNotificationRequest;
import marc.dev.payment_service.dtoRequest.PaymentRequest;
import marc.dev.payment_service.mapper.PaymentMapper;
import marc.dev.payment_service.notification.NotificationProducer;
import marc.dev.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;
    public Long createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstname(),
                        paymentRequest.customer().lastname(),
                        paymentRequest.customer().email()
                )

        );
        return payment.getId();

    }
}
