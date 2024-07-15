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

}