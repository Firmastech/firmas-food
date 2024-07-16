package danieldjgomes.larica.core.prato.controller;

import danieldjgomes.larica.core.prato.dtos.PratoRequestDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import danieldjgomes.larica.core.usecases.PratoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/prato")
@RequiredArgsConstructor
public class PratoController {

    private final PratoUseCase pratoService;

    @PostMapping("/create")
    public ResponseEntity<PratoResponseDTO> createPrato(@RequestBody @Valid PratoRequestDTO request) {
        PratoResponseDTO createdPrato = pratoService.createPrato(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrato);
    }

    @PostMapping("/{pratoId}/aplicar-desconto/{descontoId}")
    public ResponseEntity<PratoResponseDTO> aplicarDescontoAoPrato(@PathVariable UUID pratoId, @PathVariable UUID descontoId) {
        PratoResponseDTO pratoComDesconto = pratoService.applayDescontoToPrato(pratoId, descontoId);
        return ResponseEntity.ok(pratoComDesconto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> getPratoById(@PathVariable UUID id) {
        Optional<PratoResponseDTO> prato = pratoService.getPratoById(id);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> listAllPratos() {
        List<PratoResponseDTO> pratos = pratoService.listAllPratos();
        return new ResponseEntity<>(pratos, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PratoResponseDTO> updatePrato(@PathVariable UUID id, @RequestBody @Valid PratoRequestDTO updatedPrato) {
        Optional<PratoResponseDTO> prato = pratoService.updatePrato(id, updatedPrato);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePrato(@PathVariable UUID id) {
        pratoService.deletePrato(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
