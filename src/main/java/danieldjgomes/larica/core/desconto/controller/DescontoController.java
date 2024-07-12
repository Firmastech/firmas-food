package danieldjgomes.larica.core.desconto.controller;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.desconto.entity.Desconto;
import danieldjgomes.larica.core.desconto.service.DescontoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/desconto")
@RequiredArgsConstructor
public class DescontoController {

    private final DescontoService descontoService;

    @PostMapping("/create")
    public ResponseEntity<Desconto> createDesconto(@RequestBody @Valid Desconto desconto) {
        Desconto createdDesconto = descontoService.createDesconto(desconto);
        return new ResponseEntity<>(createdDesconto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Desconto>> updateDesconto(@PathVariable UUID id, @RequestBody @Valid Desconto desconto) {
        Optional<Desconto> updatedCardapio = descontoService.updateDesconto(id, desconto);
        return new ResponseEntity<>(updatedCardapio, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Desconto> getDescontoById(@PathVariable UUID id) {
        Optional<Desconto> desconto = descontoService.getDescontoById(id);
        return desconto;
    }

    @GetMapping
    public ResponseEntity<List<Desconto>> listAllDescontos() {
        List<Desconto> descontos = descontoService.listAllDescontos();
        return new ResponseEntity<>(descontos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDesconto(@PathVariable UUID id) {
        descontoService.deleteDesconto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/calcular-desconto")
    public ResponseEntity<BigDecimal> calcularDesconto(
            @RequestParam BigDecimal valorOriginal,
            @RequestParam BigDecimal porcentagemDesconto) {

        Optional<BigDecimal> valorDescontado = descontoService.aplicarDesconto(valorOriginal, porcentagemDesconto);
        return valorDescontado
                .map(valor -> ResponseEntity.ok().body(valor))
                .orElse(ResponseEntity.badRequest().build());
    }
}
