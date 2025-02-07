package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
import danieldjgomes.larica.infrastructure.PapelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificarPapelClienteNoRestauranteUseCase implements EtapaProcessoCriarUsuario {

    private final PapelRepository papelRepository;

    @Override
    public void processar(CriarUsuarioRequestDTO processo) {

    }
}
