package java12.validation.age.waiter;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = WaiterValidatorAge.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WaiterValidationAge {

    String message() default "{Должен 18 до 30 лет !}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
