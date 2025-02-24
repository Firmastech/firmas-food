package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.categoria.AtualizarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.exception.CategoriaNotFoundException;
import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.core.categoria.dtos.AtualizarCategoriaResponse;
import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AtualizarCategoriaUseCaseImpl implements AtualizarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;
    private final CategoriaMapper categoriaMapper;

    public Optional<AtualizarCategoriaResponse> updateCategoria(String id, AtualizarCategoriaRequest categoriaRequest) {
        Optional<CategoriaEntity> findByCategoria = categoriaPersist.getCategoriaById(id);
        if (findByCategoria.isEmpty()){
            throw new CategoriaNotFoundException();
        }
        Optional<CategoriaEntity> categoriaAtualizada = categoriaPersist.updateCategoria(findByCategoria.get());
        return categoriaAtualizada.map(categoriaMapper::updateEntityFromDTO);

    }
}
