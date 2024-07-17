package danieldjgomes.larica.adapter.controller;

import danieldjgomes.larica.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.usecase.pedido.ProcessarPedidoUsecaseImpl;
import danieldjgomes.larica.util.DynamicLog;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoPersistImpl pedidoPersistImpl;
    private final ProcessarPedidoUsecaseImpl processarPedidoUsecase;
    private final Logger log = LogManager.getLogger(PedidoController.class);

    @PostMapping
    public ResponseEntity<Void> createPedido(@Valid @RequestBody ProcessarPedidoRequest pedido) {
        DynamicLog.destroy();
        pedidoPersistImpl.createPedido(pedido);
        DynamicLog.put("pedido-payload", pedido);
        log.info("Pedido criado com sucesso");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{pedidoId}/processar")
    public ResponseEntity<Void> processarPedido(@PathVariable UUID pedidoId) {
        ProcessarPedidoRequest pedido = pedidoPersistImpl.getPedidoById(pedidoId);
        processarPedidoUsecase.processarPedido(pedido);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<ProcessarPedidoRequest> getPedido(@PathVariable UUID pedidoId) {
        ProcessarPedidoRequest pedido = pedidoPersistImpl.getPedidoById(pedidoId);
        return ResponseEntity.ok(pedido);
    }
}