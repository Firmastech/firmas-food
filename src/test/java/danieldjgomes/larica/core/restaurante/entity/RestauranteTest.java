package danieldjgomes.larica.core.restaurante.entity;

import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

class RestauranteTest {

    private Restaurante createRestaurante() {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante");
        restaurante.setId(UUID.randomUUID());
        restaurante.setStatusFuncionamento(StatusFuncionamento.ABERTO);
        restaurante.setTempoEstimadoDeEntrega(60);
        return restaurante;
    }

    @Test
    void NaoDeveRetornarErros() {
        Restaurante restaurante = createRestaurante();
        restaurante.validar();
        assertEquals(restaurante.getNome(), "Restaurante");
    }

    @Test
    void DeveRetornarUmaListaContendoTodosErrosDaEntidade() {
        Restaurante restaurante = createRestaurante();
        restaurante.setNome(null);
        restaurante.setId(null);
        restaurante.setStatusFuncionamento(null);
        restaurante.setTempoEstimadoDeEntrega(null);

        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            restaurante.validar();
        });

        assertEquals("ID nao pode ser nulo,\n" +
                " Nome nao pode ser nulo,\n" +
                " Tempo de entrega nao pode ser nulo,\n" +
                " Status de funcionamento nao pode ser nulo",exception.getMessage());
    }


}