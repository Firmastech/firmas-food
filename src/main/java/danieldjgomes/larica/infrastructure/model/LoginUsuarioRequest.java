package danieldjgomes.larica.infrastructure.model;

import lombok.Data;

@Data
public class LoginUsuarioRequest {

    private String usuarioId;
    private String senha;
}
