package java12.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResSumResponse {
    private String name;
    private String priceTotal;
    private String service;
    private String AverageTotal;
}
