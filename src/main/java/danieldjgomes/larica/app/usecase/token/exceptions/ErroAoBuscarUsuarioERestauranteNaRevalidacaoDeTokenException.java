package danieldjgomes.larica.app.usecase.token.exceptions;

public class ErroAoBuscarUsuarioERestauranteNaRevalidacaoDeTokenException extends RuntimeException {

  public ErroAoBuscarUsuarioERestauranteNaRevalidacaoDeTokenException(String email,
      String restaurante) {

    super("Erro ao revalidar token do par email-restaurante (" + email + "-" + restaurante + ")");
  }

}
