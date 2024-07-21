package danieldjgomes.larica.infrastructure.controller;

import danieldjgomes.larica.core.restaurante.exceptions.EntityNotFoundException;
import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@Deprecated
public class GlobalExceptionHandler {


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

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(EntityNotFoundException exception) {

        ErrorResponse dto = ErrorResponse.builder()
                .mensagens(Collections.singletonList(exception.getMessage()))
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
