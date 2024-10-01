package marc.dev.ecommerce.dtoRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import marc.dev.ecommerce.entity.Address;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstName is required")
        String firstname,
        @NotNull(message = "Customer lastName is required")
        String lastname,
        @NotNull(message = "Customer email is required")
        @Email(message = "Please enter a valid email address")
        String email,
        Address address) {

}
