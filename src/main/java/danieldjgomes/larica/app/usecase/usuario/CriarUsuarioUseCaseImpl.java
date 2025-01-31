package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final List<EtapaProcessoCriarUsuario> etapas;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CriarUsuarioUseCaseImpl(BuscarUsuarioPorEmailERestauranteUseCase buscarUsuarioPorEmailERestauranteUseCase, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {

        this.etapas = Arrays.asList(
                new VerificarUsuarioExistenteUseCase(buscarUsuarioPorEmailERestauranteUseCase),
                new VerificarRestauranteExistenteUseCase(),
                new  PersistirUsuarioUseCase(usuarioRepository, passwordEncoder)
        );
    }

    public void processar(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        for (EtapaProcessoCriarUsuario etapa : etapas) {
            etapa.processar(criarUsuarioRequestDTO);
        }

    }
}
