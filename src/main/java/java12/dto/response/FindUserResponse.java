package java12.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java12.entity.enums.Role;
import java12.validation.age.waiter.WaiterValidationAge;
import java12.validation.email.EmailValidation;
import java12.validation.experinse.waiter.WaiterValidation;
import java12.validation.password.PasswordValidation;
import java12.validation.phoneNumber.PhoneNumberValidation;

import java.time.LocalDate;

public record FindUserResponse(
                               String firstName,
                               String lastName,
                               LocalDate dateOfBirth,
                               String email,
                               String password,
                               String phoneNumber,
                               Role role,
                               int experience) {
}
