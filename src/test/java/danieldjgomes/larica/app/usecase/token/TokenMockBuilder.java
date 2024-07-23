package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.usuario.request.external.TokenAutenticacaoKeycloakModelResponseDTO;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.FederatedIdentityRepresentation;
import org.keycloak.representations.idm.UserConsentRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.*;

public class TokenMockBuilder {

    public TokenAutenticacaoKeycloakModelResponseDTO gerarMockTokenResponseKeycloak() {
        TokenAutenticacaoKeycloakModelResponseDTO tokenDTO = new TokenAutenticacaoKeycloakModelResponseDTO();
        tokenDTO.setAccessToken("exemploAccessToken");
        tokenDTO.setExpiresIn(3600);
        tokenDTO.setRefreshExpiresIn(1800);
        tokenDTO.setRefreshToken("exemploRefreshToken");
        tokenDTO.setTokenType("bearer");
        tokenDTO.setNotBeforePolicy(0);
        tokenDTO.setSessionState("exemploSessionState");
        tokenDTO.setScope("openid email profile");
        return tokenDTO;
    }
    public PegarTokenUsuarioProcessModel gerarProcessModel() {
        LoginUsuarioRequest validLoginRequest;
        validLoginRequest = new LoginUsuarioRequest();
        validLoginRequest.setEmail("user@email.com");
        validLoginRequest.setSenha("validPassword");
        PegarTokenUsuarioProcessModel processModel = new PegarTokenUsuarioProcessModel(validLoginRequest);
        processModel.setUsuarioId("6f19009d-c91b-4106-8150-66f9bdd1a3ed");
        return processModel;
    }


    public List<UserRepresentation> createMockUserRepresentation() {
        UserRepresentation userRepresentation = new UserRepresentation();

        // Preenchendo os campos com valores mock
        userRepresentation.setSelf("http://mockurl.com/user/123");
        userRepresentation.setOrigin("mockOrigin");
        userRepresentation.setCreatedTimestamp(System.currentTimeMillis());
        userRepresentation.setEnabled(true);
        userRepresentation.setFederationLink("http://mockurl.com/federation");
        userRepresentation.setServiceAccountClientId("mockServiceAccountClientId");
        userRepresentation.setUsername("6f19009d-c91b-4106-8150-66f9bdd1a3ed");

        // Mock para a lista de credenciais
        List<CredentialRepresentation> credentials = new ArrayList<>();
        credentials.add(new CredentialRepresentation()); // Adicione os detalhes do CredentialRepresentation conforme necessário
        userRepresentation.setCredentials(credentials);

        // Mock para os tipos de credenciais desativáveis
        Set<String> disableableCredentialTypes = new HashSet<>();
        disableableCredentialTypes.add("password");
        disableableCredentialTypes.add("otp");
        userRepresentation.setDisableableCredentialTypes(disableableCredentialTypes);

        // Mock para ações requeridas
        List<String> requiredActions = new ArrayList<>();
        requiredActions.add("updateProfile");
        requiredActions.add("verifyEmail");
        userRepresentation.setRequiredActions(requiredActions);

        // Mock para identidades federadas
        List<FederatedIdentityRepresentation> federatedIdentities = new ArrayList<>();
        federatedIdentities.add(new FederatedIdentityRepresentation()); // Adicione os detalhes do FederatedIdentityRepresentation conforme necessário
        userRepresentation.setFederatedIdentities(federatedIdentities);

        // Mock para papéis de realm
        List<String> realmRoles = new ArrayList<>();
        realmRoles.add("admin");
        realmRoles.add("user");
        userRepresentation.setRealmRoles(realmRoles);

        // Mock para papéis de cliente
        Map<String, List<String>> clientRoles = new HashMap<>();
        List<String> roles = new ArrayList<>();
        roles.add("role1");
        roles.add("role2");
        clientRoles.put("clientId", roles);
        userRepresentation.setClientRoles(clientRoles);

        // Mock para consentimentos de cliente
        List<UserConsentRepresentation> clientConsents = new ArrayList<>();
        clientConsents.add(new UserConsentRepresentation()); // Adicione os detalhes do UserConsentRepresentation conforme necessário
        userRepresentation.setClientConsents(clientConsents);

        // Mock para o campo notBefore
        userRepresentation.setNotBefore(123456);

        return Collections.singletonList(userRepresentation);
    }
}
