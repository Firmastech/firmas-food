package danieldjgomes.larica.usecase.pedido;

import danieldjgomes.larica.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.usecase.pedido.port.EtapaProcessamento;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class ProcessarPedidoUsecaseImpl {

    private final List<EtapaProcessamento> etapasProcessamentoList;

    public ProcessarPedidoUsecaseImpl(PedidoPersistImpl pedidoPersistImpl) {
        this.etapasProcessamentoList = Arrays.asList(
                new VerificarDisponibilidadeUsecase(pedidoPersistImpl),
                new PrepararPedidoUsecase(pedidoPersistImpl),
                new EntregarPedidoUsecase(pedidoPersistImpl),
                new ConcluirPedidoUsecase(pedidoPersistImpl)
        );
    }

    public void processarPedido(ProcessarPedidoRequest pedido) {
        for (EtapaProcessamento etapa : etapasProcessamentoList) {
            etapa.processar(pedido);
        }
    }
}