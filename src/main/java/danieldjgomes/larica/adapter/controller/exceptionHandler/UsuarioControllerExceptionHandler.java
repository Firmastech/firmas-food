package danieldjgomes.larica.adapter.controller.exceptionHandler;

import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import danieldjgomes.larica.usecase.port.usuario.exceptions.CriandoUsuarioDuplicadoException;
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
        ErrorResponse response = new ErrorResponse();
        response.setMensagens(Collections.singletonList(duplicadoException.getMessage()));
        response.setTimestamp(new Date());

        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }
}
