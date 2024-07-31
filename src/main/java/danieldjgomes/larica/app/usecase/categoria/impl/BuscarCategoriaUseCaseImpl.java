package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.categoria.BuscarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.exception.CategoriaNotFoundException;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarCategoriaUseCaseImpl implements BuscarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;

    public List<CategoriaResponse> buscarPratoList() {
        List<CategoriaEntity> categoria = categoriaPersist.listAllCategorias();
        return categoria
                .stream()
                .map(CategoriaMapper.INSTANCE::toResponse)
                .toList();
    }

    public CategoriaResponse buscarCategoria(String categoriaId) {
        CategoriaEntity categoria = categoriaPersist.getCategoriaById(categoriaId)
                .orElseThrow(CategoriaNotFoundException::new);
        return CategoriaMapper.INSTANCE.toResponse(categoria);
    }
}
