package marc.dev.payment_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import marc.dev.payment_service.dtoRequest.PaymentRequest;
import marc.dev.payment_service.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping
    public ResponseEntity<Long> createPayment(@RequestBody @Valid PaymentRequest paymentRequest){
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
    }
    @GetMapping
    public ResponseEntity<String> TestCreatePayment(){
        return ResponseEntity.ok("the payment endpoint works");
    }
}
