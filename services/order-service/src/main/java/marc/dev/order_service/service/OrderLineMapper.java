package marc.dev.order_service.service;

import marc.dev.order_service.dto.OrderLineRequest;
import marc.dev.order_service.dto.OrderLineResponse;
import marc.dev.order_service.entity.Order;
import marc.dev.order_service.entity.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return  OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .productId(orderLineRequest.productId())
                .build();

    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return  new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
