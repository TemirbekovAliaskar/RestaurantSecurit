package java12.dto.request;

import
        jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java12.entity.enums.Role;
import java12.validation.age.waiter.WaiterValidationAge;
import java12.validation.email.EmailValidation;
import java12.validation.experinse.waiter.WaiterValidation;
import java12.validation.password.PasswordValidation;
import java12.validation.phoneNumber.PhoneNumberValidation;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDate;

public record UserRequestWaiter(
        @NotNull(message = "Null !!!")
        String firstName,
        @NotNull(message = "null lastname")
        String lastName,
        @NotNull (message = "Null date!") @WaiterValidationAge
        LocalDate dateOfBirth,
        @EmailValidation @NotNull(message = "NUll email !")
        String email,
        @NotNull(message = "Null password !")  @PasswordValidation
        String password,
        @PhoneNumberValidation @NotBlank(message = "Not blank !")
        String phoneNumber,
        @NotNull(message = "Not null role !")
        Role role,
        @NotNull(message = "Not null experience !") @WaiterValidation
        int experience
) {
}
