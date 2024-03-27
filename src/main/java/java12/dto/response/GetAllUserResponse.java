package java12.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java12.entity.User;
import java12.entity.enums.Role;
import java12.validation.age.cheff.AgeValidationChef;
import java12.validation.email.EmailValidation;
import java12.validation.experinse.chef.ExperienceValidationChef;
import java12.validation.password.PasswordValidation;
import java12.validation.phoneNumber.PhoneNumberValidation;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUserResponse{
    private int page;
    private int size;
    private List<UserFindResponse> users;

}
