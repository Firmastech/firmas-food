package danieldjgomes.larica.usecase.port.usuario;

import danieldjgomes.larica.infrastructure.keycloak.dto.CriarUsuarioRequestDTO;

public interface CriarUsuarioUseCase {

    void processar(CriarUsuarioRequestDTO criarUsuarioRequestDTO);


}
