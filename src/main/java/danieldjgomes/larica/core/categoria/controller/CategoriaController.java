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
@RequestMapping("/rest/cardapios")
@RequiredArgsConstructor
public class CategoriaController {

    private final CriarCategoriaUseCase criarCategoriaUseCase;
    private final AtualizarCategoriaUseCase atualizarCategoriaUseCase;
    private final DesativarCategoriaUseCase desativarCategoriaUseCase;
    private final BuscarCategoriaUseCase buscarCategoriaUseCase;

    @PostMapping("/{cardapioId}/categorias")
    public ResponseEntity<CriarCategoriaResponse> criarCategoria(@RequestBody CriarCategoriaRequest categoriaRequest, @PathVariable String cardapioId) {
        CriarCategoriaResponse responseDTO = criarCategoriaUseCase.criar(categoriaRequest, cardapioId);
        return ResponseEntity.ok(responseDTO);
    }

    //TODO: Implementar
//    @GetMapping("/{cardapioId}/categorias/{id}")
//    public ResponseEntity<CategoriaResponse> buscarCategoria(@PathVariable String id, @PathVariable String cardapioId) {
//        CategoriaResponse responseDTO = buscarCategoriaUseCase.buscarCategoria(id);
//        return ResponseEntity.ok(responseDTO);
//    }

    @GetMapping("/{cardapioId}/categorias")
    public ResponseEntity<List<CategoriaResponse>> buscarCategorias(@PathVariable String cardapioId) {
        List<CategoriaResponse> responseDTOList = buscarCategoriaUseCase.buscarCategorias(cardapioId);
        return ResponseEntity.ok(responseDTOList);
    }

    //TODO: Implementar
//
//    @PutMapping("/{id}")
//    public ResponseEntity<AtualizarCategoriaResponse> updateCategoria(@PathVariable String id, @RequestBody AtualizarCategoriaRequest atualizarCategoriaRequest) {
//        Optional<AtualizarCategoriaResponse> responseDTO = atualizarCategoriaUseCase.updateCategoria(id, atualizarCategoriaRequest);
//        return responseDTO.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
    //TODO: Implementar
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCategoria(@PathVariable String id) {
//        desativarCategoriaUseCase.desativar(id);
//        return ResponseEntity.noContent().build();
//    }
}
