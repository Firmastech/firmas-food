package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BuscarUsuarioPorEmailERestauranteUseCaseImpl implements BuscarUsuarioPorEmailERestauranteUseCase {

    private final UsuarioRepository usuarioRepository;


    public Optional<UsuarioEntity> processar(String email, String restaurante) {
        return usuarioRepository.findAllByRestauranteIdAndEmailAndAtivoTrue(restaurante, email);
    }

}
