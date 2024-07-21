package danieldjgomes.larica.app.usecase.pedido;

import danieldjgomes.larica.app.usecase.pedido.exceptions.ProcessarPedidoException;
import danieldjgomes.larica.app.usecase.pedido.port.EtapaProcessamento;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.util.ObjectBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessarPedidoUsecaseImplTest {

    @InjectMocks
    private ProcessarPedidoUsecaseImpl processarPedidoUsecase;

    @Mock
    private EtapaProcessamento verificarDisponibilidadeUsecase;

    @Mock
    private EtapaProcessamento validarPedidoUsecase;

    @Mock
    private EtapaProcessamento processarPagamentoPedidoUsecase;

    @Mock
    private EtapaProcessamento prepararPedidoUsecase;

    @Mock
    private EtapaProcessamento entregarPedidoUsecase;

    @Mock
    private EtapaProcessamento concluirPedidoUsecase;

    private ProcessarPedidoRequest pedidoRequest;

    @BeforeEach
    void setUp() {
        pedidoRequest = ObjectBuilder.buildProcessarPedidoRequestSucesso();

        List<EtapaProcessamento> etapasProcessamentoList = Arrays.asList(
                verificarDisponibilidadeUsecase,
                validarPedidoUsecase,
                processarPagamentoPedidoUsecase,
                prepararPedidoUsecase,
                entregarPedidoUsecase,
                concluirPedidoUsecase
        );
        processarPedidoUsecase = new ProcessarPedidoUsecaseImpl(etapasProcessamentoList);
    }

    @Test
    void processarPedidoSucesso() {

        when(verificarDisponibilidadeUsecase.processar(pedidoRequest)).thenReturn(true);
        when(validarPedidoUsecase.processar(pedidoRequest)).thenReturn(true);
        when(processarPagamentoPedidoUsecase.processar(pedidoRequest)).thenReturn(true);
        when(prepararPedidoUsecase.processar(pedidoRequest)).thenReturn(true);
        when(entregarPedidoUsecase.processar(pedidoRequest)).thenReturn(true);
        when(concluirPedidoUsecase.processar(pedidoRequest)).thenReturn(true);

        assertDoesNotThrow(() -> processarPedidoUsecase.processarPedido(pedidoRequest));

        verify(verificarDisponibilidadeUsecase, times(1)).processar(pedidoRequest);
        verify(validarPedidoUsecase, times(1)).processar(pedidoRequest);
        verify(processarPagamentoPedidoUsecase, times(1)).processar(pedidoRequest);
        verify(prepararPedidoUsecase, times(1)).processar(pedidoRequest);
        verify(entregarPedidoUsecase, times(1)).processar(pedidoRequest);
        verify(concluirPedidoUsecase, times(1)).processar(pedidoRequest);
    }

    @Test
    void processarPedidoDeveRetornarFalseEncerrarFluxoAoVerificarDisponibilidade() {
        when(verificarDisponibilidadeUsecase.processar(pedidoRequest)).thenReturn(false);

        assertThrows(ProcessarPedidoException.class, () -> processarPedidoUsecase.processarPedido(pedidoRequest));

        verify(verificarDisponibilidadeUsecase, times(1)).processar(pedidoRequest);
        verify(validarPedidoUsecase, never()).processar(pedidoRequest);
    }

    @Test
    void processarPedidoDeveRetornarProcessarPedidoException() {
        when(verificarDisponibilidadeUsecase.processar(pedidoRequest)).thenReturn(false);

        assertThrows(ProcessarPedidoException.class, () -> processarPedidoUsecase.processarPedido(pedidoRequest));
    }
}