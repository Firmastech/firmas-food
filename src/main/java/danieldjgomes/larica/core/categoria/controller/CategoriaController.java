package danieldjgomes.larica.core.categoria.controller;

import danieldjgomes.larica.core.categoria.dtos.CategoriaRequestDTO;
import danieldjgomes.larica.core.categoria.dtos.CategoriaResponseDTO;
import danieldjgomes.larica.core.usecases.CategoriaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaUseCase categoriaUseCase;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> createCategoria(@RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        CategoriaResponseDTO responseDTO = categoriaUseCase.createCategoria(categoriaRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getCategoriaById(@PathVariable String id) {
        Optional<CategoriaResponseDTO> responseDTO = categoriaUseCase.getCategoriaById(id);
        return responseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAllCategorias() {
        List<CategoriaResponseDTO> responseDTOList = categoriaUseCase.listAllCategorias();
        return ResponseEntity.ok(responseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> updateCategoria(@PathVariable String id, @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        Optional<CategoriaResponseDTO> responseDTO = categoriaUseCase.updateCategoria(id, categoriaRequestDTO);
        return responseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable String id) {
        categoriaUseCase.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
