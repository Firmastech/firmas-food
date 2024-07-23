package danieldjgomes.larica.app.adapter.controller.exceptionHandler;

import danieldjgomes.larica.adapter.handlers.restaurante.CommonExceptionHandler;
import danieldjgomes.larica.app.adapter.controller.UsuarioController;
import danieldjgomes.larica.app.usecase.usuario.exceptions.CriandoUsuarioDuplicadoException;
import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.Date;

@ControllerAdvice(assignableTypes = {UsuarioController.class})
@RequiredArgsConstructor
public class UsuarioControllerExceptionHandler {

    private final CommonExceptionHandler commonExceptionHandler;

    @ExceptionHandler(CriandoUsuarioDuplicadoException.class)
    ResponseEntity<ErrorResponse> handleCriandoUsuarioDuplicadoException(CriandoUsuarioDuplicadoException duplicadoException) {
        ErrorResponse response = ErrorResponse.builder()
                .mensagens(Collections.singletonList(duplicadoException.getMessage()))
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException exception) {
        ErrorResponse dto = commonExceptionHandler.getErrorResponsetoMethodArgumentNotValidException(exception);
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
