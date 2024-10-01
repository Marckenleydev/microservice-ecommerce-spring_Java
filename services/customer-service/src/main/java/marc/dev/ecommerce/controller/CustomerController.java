package marc.dev.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import marc.dev.ecommerce.dtoRequest.CustomerRequest;
import marc.dev.ecommerce.dtoRequest.CustomerResponse;
import marc.dev.ecommerce.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping
    public  ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request){
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }
    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> existsById(@PathVariable("customerId") String customerId){
        return ResponseEntity.ok(customerService.existsById(customerId));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomer(@PathVariable("customerId") String customerId){
        return ResponseEntity.ok(customerService.findCustomer(customerId));
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
