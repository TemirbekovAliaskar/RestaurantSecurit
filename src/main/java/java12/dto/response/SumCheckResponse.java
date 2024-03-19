package java12.dto.response;

import java12.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SumCheckResponse {

    private String firstName;
    private Role role;
    private double procient;
    private String priceTotal;
    private String service;
    private String fullTotal;
}
