package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.categoria.AtualizarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AtualizarCategoriaUseCaseImpl implements AtualizarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;

    public Optional<CategoriaResponse> updateCategoria(String id, AtualizarCategoriaRequest categoriaRequest) {
        Optional<CategoriaEntity> categoriaAtualizada = categoriaPersist.updateCategoria(id, categoriaRequest);
        return categoriaAtualizada.map(CategoriaMapper.INSTANCE::updateEntityFromDTO);

    }
}
