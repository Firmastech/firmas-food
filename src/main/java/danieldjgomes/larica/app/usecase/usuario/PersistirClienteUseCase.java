package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.usecase.usuario.exceptions.PapelNaoCadastradoException;
import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
import danieldjgomes.larica.infrastructure.PapelEntity;
import danieldjgomes.larica.infrastructure.PapelRepository;
import danieldjgomes.larica.infrastructure.TipoPapel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class PersistirClienteUseCase implements EtapaProcessoCriarUsuario {

    private final UsuarioRepository usuarioRepository;
    private final PapelRepository papelRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void processar(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        UsuarioEntity novoUsuario = montarEstruturaUsuario(criarUsuarioRequestDTO, passwordEncoder);
        Optional<PapelEntity> papel = papelRepository.findByNomeAndAtivoTrue(TipoPapel.CLIENTE.toString());
        if (papel.isEmpty()) {
            throw new PapelNaoCadastradoException();
        }
        novoUsuario.setPapeis(Set.of(papel.get()));
        usuarioRepository.save(novoUsuario);
    }

    private static UsuarioEntity montarEstruturaUsuario(CriarUsuarioRequestDTO criarUsuarioRequestDTO, PasswordEncoder passwordEncoder) {
        UsuarioEntity novoUsuario = new UsuarioEntity();
        novoUsuario.setPrimeiroNome(criarUsuarioRequestDTO.getNome());
        novoUsuario.setSegundoNome(criarUsuarioRequestDTO.getSobrenome());
        novoUsuario.setEmail(criarUsuarioRequestDTO.getEmail());
        novoUsuario.setAtivo(true);
        novoUsuario.setSenha(passwordEncoder.encode(criarUsuarioRequestDTO.getSenha()));
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setId(criarUsuarioRequestDTO.getRestaurante());
        novoUsuario.setRestaurante(restaurante);
        return novoUsuario;
    }

}
