package danieldjgomes.larica.app.usecase.usuario.request;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CriarUsuarioRequestDTO {
    @Email
    @NotNull
    private String email;

    @Size(min = 8)
    @NotNull
    private String senha;

    @Size(min = 2)
    @NotNull
    private String nome;

    @Size(min = 2)
    @NotNull
    private String sobrenome;

    @Size(min = 2)
    @NotNull
    private String restaurante;
}
