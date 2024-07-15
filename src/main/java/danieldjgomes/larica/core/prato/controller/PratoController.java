package danieldjgomes.larica.core.prato.controller;

import danieldjgomes.larica.core.prato.entity.Prato;
import danieldjgomes.larica.core.prato.request.PratoRequest;
import danieldjgomes.larica.core.usecases.PratoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<Prato> createPrato(@RequestBody @Valid PratoRequest request) {
        Prato prato = modelMapper.map(request, Prato.class);
        Prato createdPrato = pratoService.createPrato(prato);
        return new ResponseEntity<>(createdPrato, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prato> getPratoById(@PathVariable UUID id) {
        Optional<Prato> prato = pratoService.getPratoById(id);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Prato>> listAllPratos() {
        List<Prato> pratos = pratoService.listAllPratos();
        return new ResponseEntity<>(pratos, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Prato> updatePrato(@PathVariable UUID id, @RequestBody @Valid Prato updatedPrato) {
        Optional<Prato> prato = pratoService.updatePrato(id, updatedPrato);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePrato(@PathVariable UUID id) {
        pratoService.deletePrato(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
