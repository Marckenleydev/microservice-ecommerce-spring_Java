package marc.dev.ecommerce.dtoRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import marc.dev.ecommerce.entity.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
