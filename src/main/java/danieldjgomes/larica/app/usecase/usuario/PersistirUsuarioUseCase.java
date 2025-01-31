package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PersistirUsuarioUseCase implements EtapaProcessoCriarUsuario {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void processar(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        UsuarioEntity novoUsuario = montarEstruturaUsuario(criarUsuarioRequestDTO, passwordEncoder);
        usuarioRepository.save(novoUsuario);
    }

    private static UsuarioEntity montarEstruturaUsuario(CriarUsuarioRequestDTO criarUsuarioRequestDTO, PasswordEncoder passwordEncoder) {
        UsuarioEntity novoUsuario = new UsuarioEntity();
        novoUsuario.setId(UUID.randomUUID().toString());
        novoUsuario.setPrimeiroNome(criarUsuarioRequestDTO.getNome());
        novoUsuario.setSegundoNome(criarUsuarioRequestDTO.getSobrenome());
        novoUsuario.setEmail(criarUsuarioRequestDTO.getEmail());
        novoUsuario.setAtivo(true);
        novoUsuario.setSenha(passwordEncoder.encode(criarUsuarioRequestDTO.getSenha()));
        return novoUsuario;
    }

}
