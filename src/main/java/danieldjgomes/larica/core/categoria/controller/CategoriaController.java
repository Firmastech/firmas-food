package danieldjgomes.larica.core.categoria.controller;


import danieldjgomes.larica.app.usecase.categoria.AtualizarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.BuscarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.CriarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.DesativarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import danieldjgomes.larica.core.categoria.dtos.AtualizarCategoriaResponse;
import danieldjgomes.larica.core.categoria.dtos.CriarCategoriaRequest;
import danieldjgomes.larica.core.categoria.dtos.CriarCategoriaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CriarCategoriaUseCase criarCategoriaUseCase;
    private final AtualizarCategoriaUseCase atualizarCategoriaUseCase;
    private final DesativarCategoriaUseCase desativarCategoriaUseCase;
    private final BuscarCategoriaUseCase buscarCategoriaUseCase;

    @PostMapping
    public ResponseEntity<CriarCategoriaResponse> createCategoria(@RequestBody CriarCategoriaRequest categoriaRequest) {
        CriarCategoriaResponse responseDTO = criarCategoriaUseCase.criar(categoriaRequest);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getCategoriaById(@PathVariable String id) {
        CategoriaResponse responseDTO = buscarCategoriaUseCase.buscarCategoria(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> getAllCategorias() {
        List<CategoriaResponse> responseDTOList = buscarCategoriaUseCase.buscarPratoList();
        return ResponseEntity.ok(responseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarCategoriaResponse> updateCategoria(@PathVariable String id, @RequestBody AtualizarCategoriaRequest atualizarCategoriaRequest) {
        Optional<AtualizarCategoriaResponse> responseDTO = atualizarCategoriaUseCase.updateCategoria(id, atualizarCategoriaRequest);
        return responseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable String id) {
        desativarCategoriaUseCase.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
