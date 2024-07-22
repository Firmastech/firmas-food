package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
import danieldjgomes.larica.app.usecase.usuario.exceptions.CriandoUsuarioDuplicadoException;
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
