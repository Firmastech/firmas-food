package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
import danieldjgomes.larica.infrastructure.PapelRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CriarClienteUseCaseImpl implements CriarClienteUseCase {

    private final List<EtapaProcessoCriarUsuario> etapas;

    public CriarClienteUseCaseImpl(BuscarUsuarioPorEmailERestauranteUseCase buscarUsuarioPorEmailERestauranteUseCase, UsuarioRepository usuarioRepository, PapelRepository papelRepository, PasswordEncoder passwordEncoder) {

        this.etapas = Arrays.asList(
                new VerificarUsuarioExistenteUseCase(buscarUsuarioPorEmailERestauranteUseCase),
                new VerificarPapelClienteNoRestauranteUseCase(papelRepository),
                new PersistirClienteUseCase(usuarioRepository, papelRepository, passwordEncoder)
        );
    }

    public void processar(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        for (EtapaProcessoCriarUsuario etapa : etapas) {
            etapa.processar(criarUsuarioRequestDTO);
        }

    }
}
