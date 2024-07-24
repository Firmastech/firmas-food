package danieldjgomes.larica.app.usecase.usuario;

import danieldjgomes.larica.app.usecase.usuario.request.CriarUsuarioRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VerificarRestauranteExistenteUseCaseTest {

    @InjectMocks
    private VerificarRestauranteExistenteUseCase verificarRestauranteExistenteUseCase;


    @Test
    public void deveProcessarSemErros() {
        CriarUsuarioRequestDTO criarUsuarioRequestDTO = new CriarUsuarioRequestDTO();
        criarUsuarioRequestDTO.setRestaurante("restauranteValido");
        criarUsuarioRequestDTO.setNome("Joao");
        criarUsuarioRequestDTO.setSobrenome("do test");
        criarUsuarioRequestDTO.setSenha("senhaMuitoSecreta");

        verificarRestauranteExistenteUseCase.processar(criarUsuarioRequestDTO);
    }


}