package java12.validation.age.waiter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class WaiterValidatorAge implements ConstraintValidator<WaiterValidationAge, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(date,currentDate).getYears();
        return age >= 15 && age <= 30;
    }
}
