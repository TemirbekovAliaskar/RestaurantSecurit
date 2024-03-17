package java12.validation.age.cheff;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java12.validation.age.cheff.AgeValidationChef;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidatorChef  implements ConstraintValidator<AgeValidationChef, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(date,currentDate).getYears();
        return age >= 25 && age <= 45;
    }
}
