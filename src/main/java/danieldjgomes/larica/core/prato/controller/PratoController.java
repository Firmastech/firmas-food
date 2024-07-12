package danieldjgomes.larica.core.prato.controller;

import danieldjgomes.larica.core.prato.entity.Prato;
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

    @PostMapping("/create")
    public ResponseEntity<Prato> createPrato(@RequestBody @Valid Prato prato) {
        Prato createdPrato = pratoService.createPrato(prato);
        return new ResponseEntity<>(createdPrato, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prato> getPratoById(@PathVariable Long id) {
        Optional<Prato> prato = pratoService.getPratoById(id);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Prato>> listAllPratos() {
        List<Prato> pratos = pratoService.listAllPratos();
        return new ResponseEntity<>(pratos, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Prato> updatePrato(@PathVariable Long id, @RequestBody @Valid Prato updatedPrato) {
        Optional<Prato> prato = pratoService.updatePrato(id, updatedPrato);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePrato(@PathVariable Long id) {
        pratoService.deletePrato(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
