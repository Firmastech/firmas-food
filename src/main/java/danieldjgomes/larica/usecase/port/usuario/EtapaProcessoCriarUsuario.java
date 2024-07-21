package danieldjgomes.larica.usecase.port.usuario;

import danieldjgomes.larica.infrastructure.keycloak.dto.CriarUsuarioRequestDTO;
import danieldjgomes.larica.usecase.port.EtapaProcessamento;

public interface EtapaProcessoCriarUsuario extends EtapaProcessamento<CriarUsuarioRequestDTO> {

}
