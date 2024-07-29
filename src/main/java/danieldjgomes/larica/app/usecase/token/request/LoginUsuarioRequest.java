package danieldjgomes.larica.app.usecase.token.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUsuarioRequest {

    @NotBlank(message = "O email nao deve ser em branco")
    private String email;
    @NotBlank(message = "O email nao deve ser em branco")
    private String senha;
    @NotBlank(message = "O restaurante nao deve ser em branco")
    private String restaurante;
}
