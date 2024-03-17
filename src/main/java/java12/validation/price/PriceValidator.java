package java12.validation.price;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<PriceValidation,Long> {

    @Override
    public boolean isValid(Long price, ConstraintValidatorContext constraintValidatorContext) {
        return price > 0;
    }
}
