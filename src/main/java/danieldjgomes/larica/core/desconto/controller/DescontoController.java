package danieldjgomes.larica.core.desconto.controller;

import danieldjgomes.larica.core.desconto.dtos.DescontoRequestDTO;
import danieldjgomes.larica.core.desconto.dtos.DescontoResponseDTO;
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
    public ResponseEntity<DescontoResponseDTO> createDesconto(@RequestBody @Valid DescontoRequestDTO descontoRequest) {
        DescontoResponseDTO createdDesconto = descontoUseCaseImpl.createDesconto(descontoRequest);
        return new ResponseEntity<>(createdDesconto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DescontoResponseDTO> updateDesconto(@PathVariable UUID id, @RequestBody @Valid DescontoRequestDTO descontoRequest) {
        Optional<DescontoResponseDTO> updatedDesconto = descontoUseCaseImpl.updateDesconto(id, descontoRequest);
        return updatedDesconto.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Optional<DescontoResponseDTO> getDescontoById(@PathVariable UUID id) {
        Optional<DescontoResponseDTO> desconto = descontoUseCaseImpl.getDescontoById(id);
        return desconto;
    }

    @GetMapping
    public List<DescontoResponseDTO> getAllDescontos() {
        return descontoUseCaseImpl.listAllDescontos();
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
