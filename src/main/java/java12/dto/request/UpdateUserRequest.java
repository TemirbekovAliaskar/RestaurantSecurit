package java12.dto.request;

import jakarta.validation.constraints.NotNull;
import java12.validation.email.EmailValidation;
import java12.validation.password.PasswordValidation;

public record UpdateUserRequest(@NotNull(message = "Null !!!")
                                String firstName,
                                @NotNull(message = "null lastname")
                                String lastName,
                                @EmailValidation @NotNull(message = "NUll email !")
                                String email,
                                @NotNull(message = "Null password !")  @PasswordValidation
                                String password) {

}
