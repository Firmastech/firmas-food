package danieldjgomes.larica.core.cardapio.exception;

import danieldjgomes.larica.infrastructure.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.Date;

@ControllerAdvice
@Slf4j
public class CardapioExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CardapioNotFoundException.class)
    @ResponseBody
    public ResponseEntity handleCardapioNotFoundException(CardapioNotFoundException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(new Date());
        response.setMensagens(Collections.singletonList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
}
