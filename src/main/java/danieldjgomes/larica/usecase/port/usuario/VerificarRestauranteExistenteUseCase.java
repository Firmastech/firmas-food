package danieldjgomes.larica.usecase.port.usuario;

import danieldjgomes.larica.infrastructure.keycloak.dto.CriarUsuarioRequestDTO;
import danieldjgomes.larica.usecase.port.usuario.exceptions.RestauranteNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerificarRestauranteExistenteUseCase implements EtapaProcessoCriarUsuario {


    @Override
    public void processar(CriarUsuarioRequestDTO processo) {
//        throw new RestauranteNaoEncontradoException();
    }


}
