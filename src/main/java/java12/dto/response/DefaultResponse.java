package java12.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;
@Builder
public record DefaultResponse(HttpStatus httpStatus,String message) {
}
