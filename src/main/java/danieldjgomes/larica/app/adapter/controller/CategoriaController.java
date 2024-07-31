package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.usecase.categoria.AtualizarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.BuscarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.CriarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.DesativarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.request.CriarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CriarCategoriaUseCase criarCategoriaUseCase;
    private final AtualizarCategoriaUseCase atualizarCategoriaUseCase;
    private final BuscarCategoriaUseCase buscarCategoriaUseCase;
    private final DesativarCategoriaUseCase desativarCategoriaUseCase;

    @PostMapping
    public ResponseEntity<CategoriaResponse> createCategoria(@RequestBody CriarCategoriaRequest criarCategoriaRequest) {
        CategoriaResponse responseDTO = criarCategoriaUseCase.criar(criarCategoriaRequest);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getCategoriaById(@PathVariable String id) {
        CategoriaResponse response = buscarCategoriaUseCase.buscarCategoria(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> getAllCategorias() {
        List<CategoriaResponse> responseDTOList = buscarCategoriaUseCase.buscarPratoList();
        return ResponseEntity.ok(responseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> updateCategoria(@PathVariable String id, @RequestBody AtualizarCategoriaRequest criarCategoriaRequest) {
        Optional<CategoriaResponse> responseDTO = atualizarCategoriaUseCase.updateCategoria(id, criarCategoriaRequest);
        return responseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable String id) {
        desativarCategoriaUseCase.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
