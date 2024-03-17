package java12.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantResponse{

    private Long id;
    private String name;
    private String location;
    private String restType;
    private int numberOfEmployees;
    private int servicePercent;
}
