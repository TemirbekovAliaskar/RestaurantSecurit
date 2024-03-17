package java12.exception.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import javax.swing.*;


@Builder
public record ExceptionResponse(
        HttpStatus httpStatus,
        String exceptionClassName,
        String message
) {
}
