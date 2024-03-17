package java12.validation.phoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java12.validation.phoneNumber.PhoneNumberValidation;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation,String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber.startsWith("+996")
                && phoneNumber.length() == 13
                && phoneNumber.substring(4).matches("^[0-9]+$");
    }
}
