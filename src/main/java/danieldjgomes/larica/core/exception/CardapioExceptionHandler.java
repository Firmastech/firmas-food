package danieldjgomes.larica.core.exception;

import danieldjgomes.larica.app.usecase.categoria.exception.CategoriaNotFoundException;
import danieldjgomes.larica.app.usecase.prato.exception.PratoNotFoundException;
import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
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
    @ExceptionHandler(CategoriaNotFoundException.class)
    @ResponseBody
    public ResponseEntity handleNotFoundException(CategoriaNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .mensagens(Collections.singletonList(ex.getMessage()))
                .timestamp(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PratoNotFoundException.class)
    @ResponseBody
    public ResponseEntity handleNotFoundException(PratoNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .mensagens(Collections.singletonList(ex.getMessage()))
                .timestamp(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
