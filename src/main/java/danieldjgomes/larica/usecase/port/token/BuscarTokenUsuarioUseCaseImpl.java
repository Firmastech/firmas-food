package danieldjgomes.larica.usecase.port.token;

import danieldjgomes.larica.infrastructure.token.KeycloakUserClient;
import danieldjgomes.larica.infrastructure.token.TokenMapper;
import danieldjgomes.larica.usecase.port.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.usecase.port.token.response.TokenResponse;
import danieldjgomes.larica.usecase.port.usuario.BuscarUsuarioPorEmailERestauranteUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BuscarTokenUsuarioUseCaseImpl implements BuscarTokenUsuarioUseCase {

    private final List<EtapaProcessoBuscarTokenUsuario> etapas;


    public BuscarTokenUsuarioUseCaseImpl(KeycloakUserClient keycloakUserClient, TokenMapper tokenMapper, BuscarUsuarioPorEmailERestauranteUseCase buscarUsuarioPorEmailERestauranteUseCase) {
        this.etapas = Arrays.asList(
                new EncontraIdentificadorDoUsuarioPorEmailERestaurante(buscarUsuarioPorEmailERestauranteUseCase),
                new BuscaTokenUsuarioNoKeycloak(keycloakUserClient, tokenMapper)
        );
    }

    @Override
    public TokenResponse processar(LoginUsuarioRequest request) {
        PegarTokenUsuarioProcessModel processModel = new PegarTokenUsuarioProcessModel(request);

        for (EtapaProcessoBuscarTokenUsuario etapa : etapas) {
            processModel = etapa.processar(processModel);
        }
        return processModel.getResultante();
    }

}
