package danieldjgomes.larica.infrastructure.keycloak;

import danieldjgomes.larica.infrastructure.TokenKeycloakAutenticacao;
import danieldjgomes.larica.infrastructure.keycloak.dto.CriarUsuarioRequestDTO;
import danieldjgomes.larica.infrastructure.keycloak.dto.LoginUsuarioKeycloakModel;
import danieldjgomes.larica.infrastructure.keycloak.exceptions.CriandoUsuarioDuplicadoException;
import danieldjgomes.larica.infrastructure.token.KeycloakUserClient;
import danieldjgomes.larica.infrastructure.token.TokenMapper;
import danieldjgomes.larica.infrastructure.token.model.LoginResponse;
import danieldjgomes.larica.infrastructure.token.model.RevalidarTokenRequest;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final KeycloakAdminBuilder keycloakAdminBuilder;
    private final KeycloakUserClient keycloakUserClient;
    private final TokenMapper tokenMapper;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;


    public Response criarUsuario(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        List<UserRepresentation> usuariosPorEmailERestaurante = buscarUsuariosPorEmailERestaurante(criarUsuarioRequestDTO.getEmail(), criarUsuarioRequestDTO.getRestaurante());
        if (usuariosPorEmailERestaurante.size() > 0) {
            throw new CriandoUsuarioDuplicadoException();
        }
        return persistirUsuario(criarUsuarioRequestDTO);
    }

    private List<UserRepresentation> buscarUsuariosPorEmailERestaurante(String email, String restaurante) {
        return keycloakAdminBuilder.getKeycloak().users().searchByAttributes(atributosParaBuscaDeUsuarioExistenteBuilder(email, restaurante));
    }

    private String atributosParaBuscaDeUsuarioExistenteBuilder(String email, String restaurante) {
        return "email:" + email + " "
                + "restaurante:" + restaurante;
    }

    private Response persistirUsuario(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        UserRepresentation novoUsuario = new UserRepresentation();
        novoUsuario.setUsername(criarUsuarioRequestDTO.getRestaurante() + "_" + criarUsuarioRequestDTO.getEmail());
        novoUsuario.setFirstName(criarUsuarioRequestDTO.getNome());
        novoUsuario.setLastName(criarUsuarioRequestDTO.getSobrenome());
        novoUsuario.setEmail(criarUsuarioRequestDTO.getEmail());
        novoUsuario.setEnabled(true);


        Map<String, List<String>> atributos = new HashMap<>();
        atributos.put("restaurante", Collections.singletonList(criarUsuarioRequestDTO.getRestaurante()));
        novoUsuario.setAttributes(atributos);

        CredentialRepresentation credential = createPasswordCredentials(criarUsuarioRequestDTO.getSenha());
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);

        novoUsuario.setCredentials(Collections.singletonList(credential));

        return keycloakAdminBuilder.getKeycloak().users().create(novoUsuario);
    }

    public List<UserRepresentation> buscarUsuarios() {
        return keycloakAdminBuilder.getKeycloak().users().list();
    }

    public LoginResponse logarUsuario(String usuarioId, String senha) {
        LoginUsuarioKeycloakModel loginUsuarioKeycloakModel = new LoginUsuarioKeycloakModel();
        loginUsuarioKeycloakModel.setClientSecret(clientSecret);
        loginUsuarioKeycloakModel.setClientId(clientId);
        loginUsuarioKeycloakModel.setGrantType("password");
        loginUsuarioKeycloakModel.setUsername(usuarioId);
        loginUsuarioKeycloakModel.setPassword(senha);

        TokenKeycloakAutenticacao tokenManager = keycloakUserClient.getUserToken(loginUsuarioKeycloakModel);
        return tokenMapper.toLoginResponse(tokenManager);
    }

    public LoginResponse revalidarToken(RevalidarTokenRequest revalidarToken) {
        RevalidarTokenKeycloakModel revalidarTokenKeycloakModel = new RevalidarTokenKeycloakModel();
        revalidarTokenKeycloakModel.setRefreshToken(revalidarToken.getToken());
        revalidarTokenKeycloakModel.setClientId(clientId);
        revalidarTokenKeycloakModel.setClientSecret(clientSecret);
        revalidarTokenKeycloakModel.setGrantType("refresh_token");

        TokenKeycloakAutenticacao tokenManager = keycloakUserClient.refreshUserToken(revalidarTokenKeycloakModel);
        return tokenMapper.toLoginResponse(tokenManager);
    }

    public static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
