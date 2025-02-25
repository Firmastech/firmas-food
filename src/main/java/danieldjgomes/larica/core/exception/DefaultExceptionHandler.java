package danieldjgomes.larica.core.exception;

import danieldjgomes.larica.app.adapter.controller.CardapioController;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.app.usecase.categoria.exception.CategoriaNotFoundException;
import danieldjgomes.larica.app.usecase.prato.exception.PratoNotFoundException;
import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
@Slf4j
@Component
public class DefaultExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoriaNotFoundException.class)
    @ResponseBody
    public ResponseEntity handleNotFoundException(CategoriaNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .mensagens(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CardapioNotFoundException.class)
    @ResponseBody
    public ResponseEntity handleNotFoundException(CardapioNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .mensagens(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PratoNotFoundException.class)
    @ResponseBody
    public ResponseEntity handleNotFoundException(PratoNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .mensagens(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
