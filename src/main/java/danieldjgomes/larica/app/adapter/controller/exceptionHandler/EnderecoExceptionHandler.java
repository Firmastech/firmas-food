package danieldjgomes.larica.app.adapter.controller.exceptionHandler;

import danieldjgomes.larica.app.usecase.endereco.exceptions.EnderecoInvalidoException;
import danieldjgomes.larica.app.usecase.endereco.exceptions.EnderecoNaoEncontradoException;
import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.View;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class EnderecoExceptionHandler {

    private final View error;

    public EnderecoExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(EnderecoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(EnderecoNaoEncontradoException exception) {

        ErrorResponse dto = ErrorResponse.builder()
                .mensagens(List.of(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EnderecoInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(EnderecoInvalidoException exception) {

        ErrorResponse dto = ErrorResponse.builder()
                .mensagens(List.of(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
