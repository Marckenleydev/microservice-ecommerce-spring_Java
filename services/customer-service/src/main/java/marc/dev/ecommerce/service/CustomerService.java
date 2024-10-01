package marc.dev.ecommerce.service;

import lombok.RequiredArgsConstructor;
import marc.dev.ecommerce.dtoRequest.CustomerRequest;
import marc.dev.ecommerce.dtoRequest.CustomerResponse;
import marc.dev.ecommerce.entity.Customer;
import marc.dev.ecommerce.exception.CustomerNotFoundException;
import marc.dev.ecommerce.mapper.CustomerMapper;
import marc.dev.ecommerce.repository.CustomerRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    protected final CustomerMapper customerMapper;
    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return  customer.getId();

    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id()).orElseThrow(()-> new CustomerNotFoundException("can not update customer::Customer not found"));


        mergerCustomer(customer, request);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());

        }
        if(StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname(request.lastname());

        }
        if(StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());

        }
        if(request.address() != null) {
            customer.setAddress(request.address());

        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return  customerRepository.findAll().stream()
                .map(customerMapper::fromCustomer).collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findCustomer(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer).orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
