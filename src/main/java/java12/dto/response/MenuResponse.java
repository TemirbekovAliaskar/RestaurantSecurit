package java12.dto.response;

import jakarta.validation.constraints.NotBlank;
import java12.validation.price.PriceValidation;

import java.util.List;

public record MenuResponse(

                           Long id,
                           String name,
                           Long price,
                           String description,
                           boolean isVegetarian

) {
}
