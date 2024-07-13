package danieldjgomes.larica.infrastructure.controller;

import danieldjgomes.larica.core.exceptions.ValidationException;
import danieldjgomes.larica.infrastructure.dto.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class ManipuladorGlobalDeErros {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> manipuladorDeErrosDeValidacao(ValidationException error) {
        ErrorResponse dto = ErrorResponse.builder()
                .id(null)
                .message(error.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
