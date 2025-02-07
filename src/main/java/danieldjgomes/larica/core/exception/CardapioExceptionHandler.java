package danieldjgomes.larica.core.exception;

import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
@Slf4j
public class CardapioExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity handleNotFoundException(EntityNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .mensagens(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
