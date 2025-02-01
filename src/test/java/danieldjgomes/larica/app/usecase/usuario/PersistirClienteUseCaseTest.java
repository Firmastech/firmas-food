package danieldjgomes.larica.app.usecase.usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersistirClienteUseCaseTest {

    @InjectMocks
    private PersistirClienteUseCase persistirClienteUseCase;

//    @Mock
//    private KeycloakAdminBuilder keycloakAdminBuilder;


    @Test
    void deveProcessar() {
//        TODO: Mudar metodo de criar usuario e implementar novo teste
//        ServerResponse serverResponse = new ServerResponse();
//        serverResponse.setStatus(HttpStatus.CREATED.value());
//        UserRepresentation userRepresentation = new UserRepresentation();
//        userRepresentation.setUsername("username");
//        when(keycloakAdminBuilder.getKeycloak().users().create(userRepresentation)).thenReturn(serverResponse);
//
//
//        CriarUsuarioRequestDTO criarUsuarioRequestDTO = new CriarUsuarioRequestDTO();
//        criarUsuarioRequestDTO.setRestaurante("restauranteValido");
//        criarUsuarioRequestDTO.setNome("Joao");
//        criarUsuarioRequestDTO.setSobrenome("do test");
//        criarUsuarioRequestDTO.setSenha("senhaMuitoSecreta");
//
//        assertDoesNotThrow(() -> persistirUsuarioNoKeycloakUseCase.processar(criarUsuarioRequestDTO));
//
    }

}