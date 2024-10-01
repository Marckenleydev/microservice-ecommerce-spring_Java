package marc.dev.order_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import marc.dev.order_service.entity.PaymentMethod;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderResponse(
        Long id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {

}
