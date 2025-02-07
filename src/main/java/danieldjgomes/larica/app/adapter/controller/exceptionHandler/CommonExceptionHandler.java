package danieldjgomes.larica.app.adapter.controller.exceptionHandler;

import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommonExceptionHandler {

    public ErrorResponse getErrorResponsetoMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add("O campo " + fieldName + ": " + errorMessage);
        });

        return ErrorResponse.builder()
                .mensagens(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
