package danieldjgomes.larica.app.usecase.pedido.config;

import danieldjgomes.larica.app.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.app.usecase.pedido.*;
import danieldjgomes.larica.app.usecase.pedido.port.EtapaProcessamento;
import danieldjgomes.larica.app.usecase.pedido.port.ProcessarPedidoUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ProcessarPedidoConfig {

    private final PedidoPersistImpl pedidoPersistImpl;

    public ProcessarPedidoConfig(PedidoPersistImpl pedidoPersistImpl) {
        this.pedidoPersistImpl = pedidoPersistImpl;
    }

    @Bean
    public ProcessarPedidoUsecase processarPedidoUsecase() {
        List<EtapaProcessamento> etapasProcessamentoList = Arrays.asList(
                new VerificarDisponibilidadeUsecase(pedidoPersistImpl),
                new ValidarPedidoUsecase(pedidoPersistImpl),
                new ProcessarPagamentoPedidoUsecase(pedidoPersistImpl),
                new PrepararPedidoUsecase(pedidoPersistImpl),
                new EntregarPedidoUsecase(pedidoPersistImpl),
                new ConcluirPedidoUsecase(pedidoPersistImpl)
        );

        return new ProcessarPedidoUsecaseImpl(etapasProcessamentoList);
    }
}
