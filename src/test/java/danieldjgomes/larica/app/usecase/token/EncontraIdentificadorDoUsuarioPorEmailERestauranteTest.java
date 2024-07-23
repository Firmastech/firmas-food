package danieldjgomes.larica.app.usecase.token;

import danieldjgomes.larica.app.usecase.token.request.LoginUsuarioRequest;
import danieldjgomes.larica.app.usecase.usuario.BuscarUsuarioPorEmailERestauranteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.FederatedIdentityRepresentation;
import org.keycloak.representations.idm.UserConsentRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EncontraIdentificadorDoUsuarioPorEmailERestauranteTest {

    @Mock
    BuscarUsuarioPorEmailERestauranteUseCase buscarUsuarioPorEmailERestauranteUseCase;

    @InjectMocks
    EncontraIdentificadorDoUsuarioPorEmailERestauranteUseCase encontraIdentificadorDoUsuarioPorEmailERestaurante;

     TokenMockBuilder tokenMockBuilder = new TokenMockBuilder();

    @Test
    public void deveProcessar() {
        when(buscarUsuarioPorEmailERestauranteUseCase.processar(any(), any())).thenReturn(tokenMockBuilder.createMockUserRepresentation());
        PegarTokenUsuarioProcessModel processo = encontraIdentificadorDoUsuarioPorEmailERestaurante.processar(tokenMockBuilder.gerarProcessModel());
        Assertions.assertEquals("6f19009d-c91b-4106-8150-66f9bdd1a3ed", processo.getUsuarioId());
    }





}