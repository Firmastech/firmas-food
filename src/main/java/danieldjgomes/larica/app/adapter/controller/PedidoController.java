package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.ports.database.PedidoPersist;
import danieldjgomes.larica.app.usecase.pedido.port.ProcessarPedidoUsecase;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.app.usecase.pedido.response.ProcessarPedidoResponse;
import danieldjgomes.larica.app.util.DynamicLog;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    //todo: fazer o response correto para cada retorno,
    private final PedidoPersist pedidoPersist;
    private final ProcessarPedidoUsecase processarPedidoUsecase;
    private final Logger log = LogManager.getLogger(PedidoController.class);

    @PostMapping
    public ResponseEntity<Void> postPedido(@Valid @RequestBody ProcessarPedidoRequest pedido) {

        DynamicLog.destroy();
        pedidoPersist.postPedido(pedido);
        DynamicLog.put("pedido-payload", pedido);
        log.info("Pedido criado com sucesso");
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/{pedidoId}/processar")
//    public ResponseEntity<Void> processarPedido(@PathVariable String pedidoId) {
//        DynamicLog.destroy();
//        ProcessarPedidoResponse pedido = pedidoPersist.getPedidoById(pedidoId);
//        DynamicLog.put("pedido-payload", pedido);
//        processarPedidoUsecase.processarPedido(pedido);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<ProcessarPedidoResponse> getPedido(@PathVariable String pedidoId) {
        ProcessarPedidoResponse pedido = pedidoPersist.getPedidoById(pedidoId);
        return ResponseEntity.ok(pedido);
    }
}