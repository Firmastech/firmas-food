package danieldjgomes.larica.adapter.handlers.endereco;

import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import danieldjgomes.larica.usecase.endereco.exceptions.EnderecoInvalidoException;
import danieldjgomes.larica.usecase.endereco.exceptions.EnderecoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.View;

import java.util.Date;
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
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EnderecoInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(EnderecoInvalidoException exception) {

        ErrorResponse dto = ErrorResponse.builder()
                .mensagens(List.of(exception.getMessage()))
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
