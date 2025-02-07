package danieldjgomes.larica.app.adapter.controller.exceptionHandler;

import danieldjgomes.larica.app.adapter.controller.restaurante.RestauranteController;
import danieldjgomes.larica.app.usecase.restaurante.exceptions.RestauranteNotFoundException;
import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice(assignableTypes = {RestauranteController.class})
@RequiredArgsConstructor
public class RestauranteExceptionHandler {

    private final CommonExceptionHandler commonExceptionHandler;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException exception) {
        ErrorResponse dto = commonExceptionHandler.getErrorResponsetoMethodArgumentNotValidException(exception);
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestauranteNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(RestauranteNotFoundException exception) {

        ErrorResponse dto = ErrorResponse.builder()
                .mensagens(List.of(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
