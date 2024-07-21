package danieldjgomes.larica.usecase.port.usuario;

import danieldjgomes.larica.infrastructure.keycloak.KeycloakAdminBuilder;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class BuscarUsuarioPorEmailERestauranteUseCaseImpl implements BuscarUsuarioPorEmailERestauranteUseCase {

    private final KeycloakAdminBuilder keycloakAdminBuilder;


    public List<UserRepresentation> processar(String email, String restaurante) {
        return keycloakAdminBuilder.getKeycloak().users().searchByAttributes(atributosParaBuscaDeUsuarioExistenteBuilder(email, restaurante));
    }

    private String atributosParaBuscaDeUsuarioExistenteBuilder(String email, String restaurante) {
        return "email:" + email + " "
                + "restaurante:" + restaurante;
    }



}
