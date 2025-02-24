package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.categoria.BuscarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.exception.CategoriaNotFoundException;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarCategoriaUseCaseImpl implements BuscarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaResponse> buscarPratoList() {
        List<CategoriaEntity> categoria = categoriaPersist.listAllCategorias();
        return categoria
                .stream()
                .map(categoriaMapper::toResponseDTO)
                .toList();
    }

    public CategoriaResponse buscarCategoria(String categoriaId) {
        CategoriaEntity categoria = categoriaPersist.getCategoriaById(categoriaId)
                .orElseThrow(CategoriaNotFoundException::new);
        return categoriaMapper.toResponseDTO(categoria);
    }
}
