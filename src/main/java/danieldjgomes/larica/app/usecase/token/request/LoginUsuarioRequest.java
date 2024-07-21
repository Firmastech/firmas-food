package danieldjgomes.larica.app.usecase.token.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUsuarioRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotBlank
    private String restaurante;
}
