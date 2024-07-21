package danieldjgomes.larica.usecase.port.usuario;

import danieldjgomes.larica.infrastructure.keycloak.KeycloakAdminBuilder;
import danieldjgomes.larica.infrastructure.keycloak.dto.CriarUsuarioRequestDTO;
import danieldjgomes.larica.usecase.port.usuario.exceptions.ErroAoPersistirUsuarioNoKeycloakException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component

public class PersistirUsuarioNoKeycloakUseCase implements EtapaProcessoCriarUsuario {

    private final KeycloakAdminBuilder keycloakAdminBuilder;

    @Override
    public void processar(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        UserRepresentation novoUsuario = montarEstruturaUsuario(criarUsuarioRequestDTO);
        atribuiRestauranteAoNovoUsuario(criarUsuarioRequestDTO.getRestaurante(), novoUsuario);
        atribuiSenhaAoNovoUsuario(criarUsuarioRequestDTO, novoUsuario);

        Response responseKeycloak = keycloakAdminBuilder.getKeycloak().users().create(novoUsuario);

        if (!HttpStatusCode.valueOf(responseKeycloak.getStatus()).is2xxSuccessful()) {
            throw new ErroAoPersistirUsuarioNoKeycloakException();
        }

    }

    private static void atribuiSenhaAoNovoUsuario(CriarUsuarioRequestDTO criarUsuarioRequestDTO, UserRepresentation novoUsuario) {
        CredentialRepresentation credential = buildPasswordCredentials(criarUsuarioRequestDTO.getSenha());
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);

        novoUsuario.setCredentials(Collections.singletonList(credential));
    }

    private static void atribuiRestauranteAoNovoUsuario(String restaurante, UserRepresentation novoUsuario) {
        Map<String, List<String>> atributos = new HashMap<>();
        atributos.put("restaurante", Collections.singletonList(restaurante));
        novoUsuario.setAttributes(atributos);
    }

    private static UserRepresentation montarEstruturaUsuario(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        UserRepresentation novoUsuario = new UserRepresentation();
        novoUsuario.setUsername(UUID.randomUUID().toString());
        novoUsuario.setFirstName(criarUsuarioRequestDTO.getNome());
        novoUsuario.setLastName(criarUsuarioRequestDTO.getSobrenome());
        novoUsuario.setEmail(criarUsuarioRequestDTO.getEmail());
        novoUsuario.setEnabled(true);
        return novoUsuario;
    }

    public static CredentialRepresentation buildPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
