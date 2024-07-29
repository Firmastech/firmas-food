package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.usecase.token.TokenMockBuilder;
import danieldjgomes.larica.app.usecase.usuario.exceptions.CriandoUsuarioDuplicadoException;
import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerificarUsuarioExistenteUseCaseTest {

    @Mock
    private BuscarUsuarioPorEmailERestauranteUseCase buscarUsuarioPorEmailERestauranteUseCase;

    @InjectMocks
    private VerificarUsuarioExistenteUseCase verificarUsuarioExistenteUseCase;

    TokenMockBuilder tokenMockBuilder = new TokenMockBuilder();



    @Test
    public void deveProcessarELancarErroDeUsuarioDuplicado() {

        CriarUsuarioRequestDTO criarUsuarioRequestDTO = new CriarUsuarioRequestDTO();
        criarUsuarioRequestDTO.setRestaurante("restauranteValido");
        criarUsuarioRequestDTO.setNome("Joao");
        criarUsuarioRequestDTO.setSobrenome("do test");
        criarUsuarioRequestDTO.setSenha("senhaMuitoSecreta");

        when(buscarUsuarioPorEmailERestauranteUseCase.processar(any(),any())).thenReturn(tokenMockBuilder.createMockUserRepresentation());
        assertThrows(CriandoUsuarioDuplicadoException.class,
                () -> verificarUsuarioExistenteUseCase.processar(criarUsuarioRequestDTO));
    }

    @Test
    public void deveProcessarSemErros() {
        CriarUsuarioRequestDTO criarUsuarioRequestDTO = new CriarUsuarioRequestDTO();
        criarUsuarioRequestDTO.setRestaurante("restauranteValido");
        criarUsuarioRequestDTO.setNome("Joao");
        criarUsuarioRequestDTO.setSobrenome("do test");
        criarUsuarioRequestDTO.setSenha("senhaMuitoSecreta");
        criarUsuarioRequestDTO.setEmail("joao@testador.com");
        when(buscarUsuarioPorEmailERestauranteUseCase.processar(any(),any())).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> verificarUsuarioExistenteUseCase.processar(criarUsuarioRequestDTO));
    }


}