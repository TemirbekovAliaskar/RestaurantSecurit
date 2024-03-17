package java12.validation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java12.validation.phoneNumber.PhoneNumberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = PasswordValidator.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidation {

    String message() default "{Password должен больще или ровно 4}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
