package marc.dev.notification_service.entity;

import lombok.*;
import marc.dev.notification_service.kafka.order.OrderConfirmation;
import marc.dev.notification_service.kafka.payment.PaymentConfirmation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Document
public class Notification {
    @Id
    private String id;
    private  NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
