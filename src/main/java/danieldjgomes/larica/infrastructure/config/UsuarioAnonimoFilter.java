package danieldjgomes.larica.infrastructure.config;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.ports.database.RestaurantePersist;
import danieldjgomes.larica.app.usecase.restaurante.port.ConsultarRestauranteUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UsuarioAnonimoFilter extends OncePerRequestFilter {

    private final RestaurantePersist restaurantePersist;

    private final AuthCache authCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioEntity anonymousUser = new UsuarioEntity();
        if (authentication == null) {
            String domain = request.getHeader("Origin");
            RestauranteEntity restaurante = authCache.get(domain);

            if (restaurante == null) {
                restaurante = restaurantePersist.findBySubdominio(domain)
                        .orElseThrow(() -> new IllegalStateException("Domínio inexistente."));
                authCache.put(domain, restaurante);
            }

            anonymousUser.setRestaurante(restaurante);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(anonymousUser, null, anonymousUser.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
