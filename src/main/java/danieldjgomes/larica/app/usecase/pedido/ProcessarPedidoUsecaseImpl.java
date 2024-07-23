package danieldjgomes.larica.app.usecase.pedido;

import danieldjgomes.larica.app.usecase.pedido.exceptions.ProcessarPedidoException;
import danieldjgomes.larica.app.usecase.pedido.port.EtapaProcessamento;
import danieldjgomes.larica.app.usecase.pedido.port.ProcessarPedidoUsecase;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessarPedidoUsecaseImpl implements ProcessarPedidoUsecase {
    // Atualização do Pedido: O sistema atualiza o status do pedido no
    // banco de dados após cada etapa do processamento do pedido.

    // Confirmação do Pedido: Após o cliente criar um pedido,
    // você pode querer enviar uma confirmação para o cliente.
    // Isso pode ser feito por meio de um e-mail, SMS ou notificação no aplicativo.

    //Retentativas de Entrega: Se a entrega do pedido falhar (por exemplo, se o cliente
    // não estiver disponível para receber o pedido), você pode precisar de uma maneira
    // de tentar a entrega novamente.

    //Avaliação do Pedido: Após a conclusão do pedido, você pode querer permitir que o
    // cliente avalie o pedido. Isso pode envolver adicionar uma nova etapa no fluxo de
    // pedidos após a conclusão do pedido.

    //Notificações de Status: Durante cada etapa do fluxo de pedidos, você pode querer
    // enviar notificações para o cliente para informá-lo sobre o status atual do pedido.
    private final List<EtapaProcessamento> etapasProcessamentoList;
    private final Logger log = LogManager.getLogger(ProcessarPedidoUsecaseImpl.class);

    @Override
    public void processarPedido(ProcessarPedidoRequest pedido) {
        log.info("Iniciando o processamento do pedido {}", pedido.getId());
        boolean todasEtapasBemSucedidas = etapasProcessamentoList.stream()
                .allMatch(etapa -> etapa.processar(pedido));
        if (!todasEtapasBemSucedidas) {
            throw new ProcessarPedidoException();
        }
        log.info("Pedido processado com sucesso");
    }
}