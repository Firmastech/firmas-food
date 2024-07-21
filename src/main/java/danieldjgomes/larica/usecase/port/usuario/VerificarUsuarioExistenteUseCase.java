package danieldjgomes.larica.usecase.port.usuario;

import danieldjgomes.larica.infrastructure.keycloak.dto.CriarUsuarioRequestDTO;
import danieldjgomes.larica.usecase.port.usuario.exceptions.CriandoUsuarioDuplicadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificarUsuarioExistenteUseCase implements EtapaProcessoCriarUsuario {

    private final BuscarUsuarioPorEmailERestauranteUseCase buscarUsuarioPorEmailERestauranteUseCase;

    @Override
    public void processar(CriarUsuarioRequestDTO processo) {
        if (buscarUsuarioPorEmailERestauranteUseCase.processar(processo.getEmail(), processo.getRestaurante()).size() > 0) {
            throw new CriandoUsuarioDuplicadoException();
        }
    }

}
