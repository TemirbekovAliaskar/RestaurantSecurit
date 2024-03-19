package java12.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java12.entity.enums.Role;

import java.time.LocalDate;

public record UserFindResponse(

        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String email ,
        String password,
        String phoneNumber,
        Role role,
        int experience
) {
}
