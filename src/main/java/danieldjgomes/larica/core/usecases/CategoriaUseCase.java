package danieldjgomes.larica.core.usecases;

import danieldjgomes.larica.core.categoria.dtos.CategoriaRequestDTO;
import danieldjgomes.larica.core.categoria.dtos.CategoriaResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CategoriaUseCase {

    CategoriaResponseDTO createCategoria(CategoriaRequestDTO categoriaRequest);

    Optional<CategoriaResponseDTO> getCategoriaById(String id);

    List<CategoriaResponseDTO> listAllCategorias();

    Optional<CategoriaResponseDTO> updateCategoria(String id, CategoriaRequestDTO categoriaRequest);

    void deleteCategoria(String id);
}
