package danieldjgomes.larica.usecase.port.usuario;

import danieldjgomes.larica.infrastructure.keycloak.KeycloakAdminBuilder;
import danieldjgomes.larica.infrastructure.keycloak.dto.CriarUsuarioRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final List<EtapaProcessoCriarUsuario> etapas;

    public CriarUsuarioUseCaseImpl(KeycloakAdminBuilder keycloakAdminBuilder, BuscarUsuarioPorEmailERestauranteUseCase buscarUsuarioPorEmailERestauranteUseCase) {

        this.etapas = Arrays.asList(
                new VerificarUsuarioExistenteUseCase(buscarUsuarioPorEmailERestauranteUseCase),
                new VerificarRestauranteExistenteUseCase(),
                new PersistirUsuarioNoKeycloakUseCase(keycloakAdminBuilder)
        );
    }

    public void processar(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        for (EtapaProcessoCriarUsuario etapa : etapas) {
            etapa.processar(criarUsuarioRequestDTO);
        }

    }
}
