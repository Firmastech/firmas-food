package danieldjgomes.larica.infrastructure.token.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUsuarioRequest {

    @NotBlank
    private String usuarioId;
    @NotBlank
    private String senha;
}
