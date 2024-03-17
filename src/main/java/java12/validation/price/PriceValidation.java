package java12.validation.price;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java12.validation.phoneNumber.PhoneNumberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = PriceValidator.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceValidation {

    String message() default "{ Цена не должен быть минусовой значение !!!}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
