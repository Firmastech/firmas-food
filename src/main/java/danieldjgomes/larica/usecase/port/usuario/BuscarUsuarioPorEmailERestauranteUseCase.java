package danieldjgomes.larica.usecase.port.usuario;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface BuscarUsuarioPorEmailERestauranteUseCase {

    List<UserRepresentation> processar(String email, String restaurante);
}
