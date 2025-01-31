package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] parts = username.split(":");
        String email = parts[0];
        String restaurante = parts[1];
        return usuarioRepository.findAllByRestauranteIdAndEmailAndAtivoTrue(restaurante, email).orElseThrow();
    }
}
