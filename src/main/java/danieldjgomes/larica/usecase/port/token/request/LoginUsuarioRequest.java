package danieldjgomes.larica.usecase.port.token.request;

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
