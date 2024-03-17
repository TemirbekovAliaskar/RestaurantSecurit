package java12.validation.experinse.waiter;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java12.validation.experinse.chef.ExperienceValidatorChef;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = WaiterValidator.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WaiterValidation {
    String message() default "{Стаж должен больше 1год! }";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
