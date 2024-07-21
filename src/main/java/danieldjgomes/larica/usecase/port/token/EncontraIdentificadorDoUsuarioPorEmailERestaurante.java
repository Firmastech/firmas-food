package danieldjgomes.larica.usecase.port.token;

import danieldjgomes.larica.usecase.port.usuario.BuscarUsuarioPorEmailERestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncontraIdentificadorDoUsuarioPorEmailERestaurante implements EtapaProcessoBuscarTokenUsuario {

    private final BuscarUsuarioPorEmailERestauranteUseCase buscarUsuarioPorEmailERestauranteUseCase;

    @Override
    public PegarTokenUsuarioProcessModel processar(PegarTokenUsuarioProcessModel processo) {
        UserRepresentation usuario = buscarUsuarioPorEmailERestauranteUseCase.processar(processo.getEntrada().getEmail(), processo.getEntrada().getRestaurante()).get(0);
        processo.setUsuarioId(usuario.getUsername());
        return processo;
    }
}
