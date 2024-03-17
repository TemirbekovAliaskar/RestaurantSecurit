package java12.validation.experinse.waiter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WaiterValidator implements ConstraintValidator<WaiterValidation,Integer> {
    @Override
    public boolean isValid(Integer experience, ConstraintValidatorContext constraintValidatorContext) {
        return experience >= 1;
    }
}