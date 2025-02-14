package danieldjgomes.larica.app.adapter.controller.exceptionHandler;

import danieldjgomes.larica.app.adapter.controller.TokenController;
import danieldjgomes.larica.app.usecase.token.exceptions.ErroAoBuscarUsuarioERestauranteNaRevalidacaoDeTokenException;
import danieldjgomes.larica.app.usecase.token.exceptions.ErroAoMontarTokenException;
import danieldjgomes.larica.infrastructure.expectionHandler.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {TokenController.class})
@RequiredArgsConstructor
@Slf4j
public class TokenControllerExceptionHandler {

  private final CommonExceptionHandler commonExceptionHandler;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> erroAoRevalidarTokenRequestException(
      MethodArgumentNotValidException exception) {
    ErrorResponse dto = commonExceptionHandler.getErrorResponsetoMethodArgumentNotValidException(
        exception);
    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ErroAoBuscarUsuarioERestauranteNaRevalidacaoDeTokenException.class)
  public ResponseEntity<ErrorResponse> erroAoRevalidarTokenRequestException(
      ErroAoBuscarUsuarioERestauranteNaRevalidacaoDeTokenException exception) {
    log.debug(exception.getMessage());
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  }
}
