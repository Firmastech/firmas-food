package danieldjgomes.larica.infrastructure.keycloak.exceptions;

import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@ControllerAdvice
@Slf4j
public class KeycloakExceptionHandler {

    @ExceptionHandler(CriandoUsuarioDuplicadoException.class)
    public ResponseEntity criandoUsuarioDuplicadoExceptionHandler(CriandoUsuarioDuplicadoException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(new Date());
        response.setMensagens(Collections.singletonList(exception.getMessage()));
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        log.info(exception.getMessage());
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(new Date());
        response.setMensagens(Arrays.asList(exception.getSuppressedFields())); //TODO: fix
        return ResponseEntity.badRequest().body(response);
    }
}
