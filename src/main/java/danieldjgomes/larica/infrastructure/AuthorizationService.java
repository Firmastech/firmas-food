package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public static String findRestaurante(){
        UsuarioEntity usuario = (UsuarioEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuario.getRestaurante().getId();
    }
}
