package danieldjgomes.larica.app.adapter.controller;


import danieldjgomes.larica.app.usecase.categoria.AtualizarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.BuscarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.CriarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.DesativarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.AtualizarCategoriaResponse;
import danieldjgomes.larica.app.usecase.categoria.response.BuscarDetalhesCategoriaResponse;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import danieldjgomes.larica.app.usecase.categoria.request.CriarCategoriaRequest;
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
    public ResponseEntity<CategoriaResponse> criarCategoria(@RequestBody CriarCategoriaRequest categoriaRequest, @PathVariable String cardapioId) {
        CategoriaResponse responseDTO = criarCategoriaUseCase.criar(categoriaRequest, cardapioId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{cardapioId}/categorias/{categoriaId}")
    public ResponseEntity<BuscarDetalhesCategoriaResponse> buscarDetalhesCategoria(@PathVariable String categoriaId, @PathVariable String cardapioId) {
        BuscarDetalhesCategoriaResponse responseDTO = buscarCategoriaUseCase.buscarDetalhesCategoria(categoriaId, cardapioId);
        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/{cardapioId}/categorias")
    public ResponseEntity<List<CategoriaResponse>> buscarCategorias(@PathVariable String cardapioId) {
        List<CategoriaResponse> responseDTOList = buscarCategoriaUseCase.buscarCategorias(cardapioId);
        return ResponseEntity.ok(responseDTOList);
    }

    @PutMapping("/{cardapioId}/categorias/{categoriaId}")
    public ResponseEntity<AtualizarCategoriaResponse> updateCategoria(@PathVariable String categoriaId, @RequestBody AtualizarCategoriaRequest atualizarCategoriaRequest) {
        Optional<AtualizarCategoriaResponse> responseDTO = atualizarCategoriaUseCase.updateCategoria(categoriaId, atualizarCategoriaRequest);
        return responseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cardapioId}/categorias/{categoriaId}")
    public ResponseEntity<Void> desativarCategoria(@PathVariable String categoriaId) {
        desativarCategoriaUseCase.desativar(categoriaId);
        return ResponseEntity.noContent().build();
    }
}
