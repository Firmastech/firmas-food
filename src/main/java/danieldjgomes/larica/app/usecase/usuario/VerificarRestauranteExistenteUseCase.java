package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
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
