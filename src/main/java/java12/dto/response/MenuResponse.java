package java12.dto.response;

import jakarta.validation.constraints.NotBlank;
import java12.validation.price.PriceValidation;

public record MenuResponse(
                           String name,
                           Long price,
                           String description,
                           boolean isVegetarian) {
}
