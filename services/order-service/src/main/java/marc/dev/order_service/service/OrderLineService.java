package marc.dev.order_service.service;

import lombok.RequiredArgsConstructor;
import marc.dev.order_service.dto.OrderLineRequest;
import marc.dev.order_service.dto.OrderLineResponse;
import marc.dev.order_service.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private  final OrderLineRepository orderLineRepository;
    private  final OrderLineMapper orderLineMapper;
    public Long saveOrderLine(OrderLineRequest orderLineRequest) {
        var order =orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();

    }

    public List<OrderLineResponse> findAllByOrderId(Long orderId) {
        return  orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
