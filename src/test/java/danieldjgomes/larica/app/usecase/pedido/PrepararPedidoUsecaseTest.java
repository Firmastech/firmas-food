package danieldjgomes.larica.app.usecase.pedido;

import danieldjgomes.larica.app.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.util.ObjectBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrepararPedidoUsecaseTest {

    @InjectMocks
    private PrepararPedidoUsecase prepararPedidoUsecase;

    @Mock
    private PedidoPersistImpl pedidoPersistImpl;

    private ProcessarPedidoRequest pedidoRequest;

    @BeforeEach
    void setUp() {
        pedidoRequest = ObjectBuilder.buildProcessarPedidoRequestSucesso();
    }

    @Test
    void processarSucesso() {
        assertDoesNotThrow(() -> prepararPedidoUsecase.processar(pedidoRequest));
    }
}