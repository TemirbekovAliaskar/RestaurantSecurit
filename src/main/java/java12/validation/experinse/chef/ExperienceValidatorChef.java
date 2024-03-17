package java12.validation.experinse.chef;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java12.validation.experinse.chef.ExperienceValidationChef;

public class ExperienceValidatorChef implements ConstraintValidator<ExperienceValidationChef,Integer> {
    @Override
    public boolean isValid(Integer experience, ConstraintValidatorContext constraintValidatorContext) {
        return experience >= 2;
    }
}
