package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.categoria.AtualizarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.exception.CategoriaNotFoundException;
import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.AtualizarCategoriaResponse;
import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AtualizarCategoriaUseCaseImpl implements AtualizarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;
    private final CategoriaMapper categoriaMapper;

    public Optional<AtualizarCategoriaResponse> updateCategoria(String categoriaId, AtualizarCategoriaRequest atualizarCategoriaRequest) {

        Optional<CategoriaEntity> buscarCategoria = categoriaPersist.buscarDetalhesCategoria(categoriaId);

        CategoriaEntity categoriaParaAtualizar = buscarCategoria
                .map(c -> {
                    c.setNome(atualizarCategoriaRequest.getNome());
                    return c;
                })
                .orElseThrow(CategoriaNotFoundException::new);

        Optional<CategoriaEntity> categoriaAtualizada = categoriaPersist.updateCategoria(categoriaParaAtualizar);
        return categoriaAtualizada.map(categoriaMapper::updateEntityFromDTO);

    }
}
