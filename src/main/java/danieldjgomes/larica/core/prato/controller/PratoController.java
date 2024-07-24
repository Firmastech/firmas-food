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

@RestController
@RequestMapping("/api/prato")
@RequiredArgsConstructor
public class PratoController {

    private final PratoUseCase pratoService;

    @PostMapping()
    public ResponseEntity<PratoResponseDTO> createPrato(@RequestBody @Valid PratoRequestDTO request) {
        PratoResponseDTO createdPrato = pratoService.createPrato(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrato);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> getPratoById(@PathVariable String id) {
        Optional<PratoResponseDTO> prato = pratoService.findPratoById(id);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> listAllPratos() {
        List<PratoResponseDTO> pratos = pratoService.getAllPratos();
        return new ResponseEntity<>(pratos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> updatePrato(
            @PathVariable String id, @RequestBody @Valid PratoRequestDTO updatedPrato) {
        Optional<PratoResponseDTO> prato = pratoService.updatePrato(id, updatedPrato);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrato(@PathVariable String id) {
        pratoService.disablePrato(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
