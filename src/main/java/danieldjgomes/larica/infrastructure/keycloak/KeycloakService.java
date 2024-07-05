package danieldjgomes.larica.infrastructure.keycloak;

import danieldjgomes.larica.infrastructure.keycloak.dto.CriarUsuarioRequestDTO;
import danieldjgomes.larica.infrastructure.keycloak.exceptions.CriandoUsuarioDuplicadoException;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakService {

    @Value("${keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;

    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public Response criarUsuario(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        List<UserRepresentation> usuariosPorEmailERestaurante = buscarUsuariosPorEmailERestaurante(criarUsuarioRequestDTO.getEmail(), criarUsuarioRequestDTO.getRestaurante());
        if(usuariosPorEmailERestaurante.size() > 0){
            throw new CriandoUsuarioDuplicadoException();
        }
        return persistirUsuario(criarUsuarioRequestDTO);
    }

    private List<UserRepresentation> buscarUsuariosPorEmailERestaurante(String email, String restaurante) {
        return keycloak.realm(realm).users().searchByAttributes(atributosParaBuscaDeUsuarioExistenteBuilder(email, restaurante));
    }

    private String atributosParaBuscaDeUsuarioExistenteBuilder(String email, String restaurante) {
        return      "email:" + email + " "
                +   "restaurante:" + restaurante;
    }

    private Response persistirUsuario(CriarUsuarioRequestDTO criarUsuarioRequestDTO) {
        UserRepresentation novoUsuario = new UserRepresentation();
        novoUsuario.setUsername(criarUsuarioRequestDTO.getRestaurante() + "_" + criarUsuarioRequestDTO.getEmail());
        novoUsuario.setFirstName(criarUsuarioRequestDTO.getNome());
        novoUsuario.setLastName(criarUsuarioRequestDTO.getSobrenome());
        novoUsuario.setEmail(criarUsuarioRequestDTO.getEmail());
        novoUsuario.setEnabled(true);


        Map<String, List<String>> atributos = new HashMap<>();
        atributos.put("restaurante",Collections.singletonList(criarUsuarioRequestDTO.getRestaurante()));
        novoUsuario.setAttributes(atributos);

        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(criarUsuarioRequestDTO.getSenha());
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);

        novoUsuario.setCredentials(Collections.singletonList(credential));

        return keycloak.realm(realm).users().create(novoUsuario);
    }

    public List<UserRepresentation> buscarUsuarios() {
        return keycloak.realm(realm).users().list();
    }
}
