package marc.dev.order_service.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marc.dev.order_service.customer.CustomerClient;
import marc.dev.order_service.dto.*;
import marc.dev.order_service.dtoRequest.PaymentRequest;
import marc.dev.order_service.exception.BusinessException;
import marc.dev.order_service.kafka.OrderConfirmation;
import marc.dev.order_service.kafka.OrderProducer;
import marc.dev.order_service.payment.PaymentClient;
import marc.dev.order_service.product.ProductClient;
import marc.dev.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private  final CustomerClient customerClient;
    private  final ProductClient productClient;
    private  final OrderRepository orderRepository;
    private  final OrderMapper orderMapper;
    private  final OrderLineService orderLineService;
    private  final OrderProducer orderProducer;
    private  final PaymentClient paymentClient;
    public Long createOrder(OrderRequest orderRequest) {
        // check the customer -> OpenFeign
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order:: Customer not found with provide id: " ));
     log.info(customer.id());
     log.info(customer.firstname() + customer.lastname());
        //purchase the product
         var purchaseProducts = this.productClient.purchaseProducts(orderRequest.products());
        // persist order
        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        // persist order lines
        for(PurchaseRequest purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        // send the order notification using kafka
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Long orderId) {
        return  orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException("Order not found"));
    }
}
