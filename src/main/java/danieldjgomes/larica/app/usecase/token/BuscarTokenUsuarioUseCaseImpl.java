package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.token.response.TokenResponse;
import danieldjgomes.larica.app.usecase.usuario.BuscarUsuarioPorEmailERestauranteUseCase;
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
