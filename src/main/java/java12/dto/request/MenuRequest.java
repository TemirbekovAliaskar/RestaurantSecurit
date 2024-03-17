package java12.dto.request;

import jakarta.validation.constraints.NotBlank;
import java12.validation.price.PriceValidation;

public record MenuRequest(
                          @NotBlank
                          String name,
                          @PriceValidation
                          Long price,
                          @NotBlank
                          String description,
                          boolean isVegetarian) {
}
