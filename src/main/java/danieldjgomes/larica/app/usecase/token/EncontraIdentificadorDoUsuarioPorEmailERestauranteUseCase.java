package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.usuario.BuscarUsuarioPorEmailERestauranteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncontraIdentificadorDoUsuarioPorEmailERestauranteUseCase implements EtapaProcessoBuscarTokenUsuario {

    private final BuscarUsuarioPorEmailERestauranteUseCase buscarUsuarioPorEmailERestauranteUseCase;

    @Override
    public PegarTokenUsuarioProcessModel processar(PegarTokenUsuarioProcessModel processo) {
//        UserRepresentation usuario = buscarUsuarioPorEmailERestauranteUseCase.processar(processo.getEntrada().getEmail(), processo.getRestauranteId()).get(0);
//        processo.setUsuarioId(usuario.getUsername());
        return processo;
    }
}
