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

@ExtendWith(MockitoExtension.class)
class ConcluirPedidoUsecaseTest {

    @InjectMocks
    private ConcluirPedidoUsecase concluirPedidoUsecase;

    @Mock
    private PedidoPersistImpl pedidoPersistImpl;

    private ProcessarPedidoRequest pedidoRequest;

    @BeforeEach
    void setUp() {
        pedidoRequest = ObjectBuilder.buildProcessarPedidoRequestSucesso();
        pedidoPersistImpl.getPedidoById(pedidoRequest.getId());
    }

    @Test
    void processarSucesso() {
        assertDoesNotThrow(() -> concluirPedidoUsecase.processar(pedidoRequest));
    }
}