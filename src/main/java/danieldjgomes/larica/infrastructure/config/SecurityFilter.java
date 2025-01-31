package danieldjgomes.larica.infrastructure.config;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.repository.UsuarioRepository;
import danieldjgomes.larica.app.usecase.token.ValidarEmailNoTokenUseCase;
import danieldjgomes.larica.app.usecase.token.ValidarRestauranteNoTokenUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UsuarioRepository usuarioRepository;
    private final ValidarRestauranteNoTokenUseCase validarRestauranteNoTokenUseCase;
    private final ValidarEmailNoTokenUseCase validarEmailNoTokenUseCase;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null) {
            String email = validarEmailNoTokenUseCase.validar(token);
            String restauranteId = validarRestauranteNoTokenUseCase.validar(token);
            Optional<UsuarioEntity> usuario = usuarioRepository.findAllByRestauranteIdAndEmailAndAtivoTrue(restauranteId, email);
            if (usuario.isPresent()) {
                var authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null, usuario.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            return null;
        }
        return authorization.replace("Bearer ", "");
    }
}
