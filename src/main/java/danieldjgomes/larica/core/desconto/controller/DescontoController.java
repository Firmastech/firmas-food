package danieldjgomes.larica.core.desconto.controller;

import danieldjgomes.larica.core.desconto.entity.Desconto;
import danieldjgomes.larica.core.desconto.service.DescontoUseCaseImpl;
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

    private final DescontoUseCaseImpl descontoUseCaseImpl;

    @PostMapping("/create")
    public ResponseEntity<Desconto> createDesconto(@RequestBody Desconto desconto) {
        Desconto createdDesconto = descontoUseCaseImpl.createDesconto(desconto);
        return new ResponseEntity<>(createdDesconto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Desconto>> updateDesconto(@PathVariable UUID id, @RequestBody @Valid Desconto desconto) {
        Optional<Desconto> updatedCardapio = descontoUseCaseImpl.updateDesconto(id, desconto);
        return new ResponseEntity<>(updatedCardapio, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Desconto> getDescontoById(@PathVariable UUID id) {
        Optional<Desconto> desconto = descontoUseCaseImpl.getDescontoById(id);
        return desconto;
    }

    @GetMapping
    public ResponseEntity<List<Desconto>> listAllDescontos() {
        List<Desconto> descontos = descontoUseCaseImpl.listAllDescontos();
        return new ResponseEntity<>(descontos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDesconto(@PathVariable UUID id) {
        descontoUseCaseImpl.deleteDesconto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/calcular-desconto")
    public ResponseEntity<BigDecimal> calcularDesconto(
            @RequestParam BigDecimal valorOriginal,
            @RequestParam BigDecimal porcentagemDesconto) {

        Optional<BigDecimal> valorDescontado = descontoUseCaseImpl.aplicarDesconto(valorOriginal, porcentagemDesconto);
        return valorDescontado
                .map(valor -> ResponseEntity.ok().body(valor))
                .orElse(ResponseEntity.badRequest().build());
    }
}
