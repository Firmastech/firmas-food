package danieldjgomes.larica.app.adapter.controller.exceptionHandler;

import danieldjgomes.larica.adapter.handlers.restaurante.CommonExceptionHandler;
import danieldjgomes.larica.app.adapter.controller.TokenController;
import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = {TokenController.class})
@RequiredArgsConstructor
public class TokenControllerExceptionHandler {

    private final CommonExceptionHandler commonExceptionHandler;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException exception) {
        ErrorResponse dto = commonExceptionHandler.getErrorResponsetoMethodArgumentNotValidException(exception);
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
