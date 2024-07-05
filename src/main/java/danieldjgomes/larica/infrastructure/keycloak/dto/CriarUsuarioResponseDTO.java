package danieldjgomes.larica.infrastructure.keycloak.dto;


import lombok.Data;

@Data
public class CriarUsuarioResponseDTO {

    private String usuarioId;
    private String emailId;
    private String nome;
    private String sobrenome;
    private String restaurante;
}
