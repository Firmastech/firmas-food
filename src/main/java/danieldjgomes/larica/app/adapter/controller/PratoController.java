package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.usecase.prato.AtualizarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.BuscarPratosUseCase;
import danieldjgomes.larica.app.usecase.prato.CriarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.DesativarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.reqeust.AtualizarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.reqeust.CriarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;
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

    private final CriarPratoUseCase criarPratoUseCase;
    private final BuscarPratosUseCase buscarPratosUseCase;
    private final AtualizarPratoUseCase atualizarPratoUseCase;
    private final DesativarPratoUseCase desativarPratoUseCase;

    @PostMapping()
    public ResponseEntity<PratoResponse> createPrato(@RequestBody @Valid CriarPratoRequest request) {
        PratoResponse createdPrato = criarPratoUseCase.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrato);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PratoResponse> getPratoById(@PathVariable String id) {
        PratoResponse prato = buscarPratosUseCase.buscarPrato(id);
        return ResponseEntity.ok(prato);
    }

    @GetMapping
    public ResponseEntity<List<PratoResponse>> listAllPratos() {
        List<PratoResponse> pratos = buscarPratosUseCase.buscarPratoList();
        return new ResponseEntity<>(pratos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PratoResponse> updatePrato(
            @PathVariable String id, @RequestBody @Valid AtualizarPratoRequest updatedPrato) {
        Optional<PratoResponse> prato = atualizarPratoUseCase.updatePrato(id, updatedPrato);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrato(@PathVariable String id) {
        desativarPratoUseCase.desativar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
