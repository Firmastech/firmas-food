package danieldjgomes.larica.core.prato.controller;

import danieldjgomes.larica.core.prato.dtos.PratoRequestDTO;
import danieldjgomes.larica.core.prato.dtos.PratoResponseDTO;
import danieldjgomes.larica.core.usecases.PratoUseCase;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pratos")
@RequiredArgsConstructor

public class PratoController {

    private final PratoUseCase pratoService;

    @PostMapping()
    @PreAuthorize("hasAuthority('CRIAR_PRATO')")
    public ResponseEntity<PratoResponseDTO> createPrato(@RequestBody @Valid PratoRequestDTO request) {
        PratoResponseDTO createdPrato = pratoService.createPrato(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrato);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_PRATOS')")
    public ResponseEntity<PratoResponseDTO> getPratoById(@PathVariable String id) {
        Optional<PratoResponseDTO> prato = pratoService.getPratoById(id);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_PRATOS')")
    public ResponseEntity<List<PratoResponseDTO>> listAllPratos() {
        List<PratoResponseDTO> pratos = pratoService.getAllPratos();
        return new ResponseEntity<>(pratos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_PRATO')")
    public ResponseEntity<PratoResponseDTO> updatePrato(@PathVariable String id, @RequestBody @Valid PratoRequestDTO updatedPrato) {
        Optional<PratoResponseDTO> prato = pratoService.updatePrato(id, updatedPrato);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DESABILITAR_PRATO')")
    public ResponseEntity<Void> deletePrato(@PathVariable String id) {
        pratoService.deletePrato(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
