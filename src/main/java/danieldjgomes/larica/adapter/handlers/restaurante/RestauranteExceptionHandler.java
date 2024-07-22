package danieldjgomes.larica.adapter.handlers.restaurante;

import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import danieldjgomes.larica.usecase.restaurante.exceptions.RestauranteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.View;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestauranteExceptionHandler {

    private final View error;

    public RestauranteExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add("O campo " + fieldName + ": " + errorMessage);
        });

        ErrorResponse dto = ErrorResponse.builder()
                .mensagens(errors)
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestauranteNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(RestauranteNotFoundException exception) {

        ErrorResponse dto = ErrorResponse.builder()
                .mensagens(List.of(exception.getMessage()))
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
