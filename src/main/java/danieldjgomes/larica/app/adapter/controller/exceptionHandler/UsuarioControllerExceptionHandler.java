package danieldjgomes.larica.app.adapter.controller.exceptionHandler;

import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import danieldjgomes.larica.app.usecase.usuario.exceptions.CriandoUsuarioDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Date;

@ControllerAdvice
public class UsuarioControllerExceptionHandler {


    @ExceptionHandler(CriandoUsuarioDuplicadoException.class)
    ResponseEntity<ErrorResponse> handleCriandoUsuarioDuplicadoException(CriandoUsuarioDuplicadoException duplicadoException){
        ErrorResponse response = ErrorResponse.builder()
                        .mensagens(Collections.singletonList(duplicadoException.getMessage()))
                                .timestamp(new Date())
                                        .build();
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }
}
