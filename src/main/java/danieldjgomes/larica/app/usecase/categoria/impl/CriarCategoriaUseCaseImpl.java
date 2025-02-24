package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.GerarUUIDUseCase;
import danieldjgomes.larica.app.usecase.categoria.CriarCategoriaUseCase;
import danieldjgomes.larica.core.categoria.dtos.CriarCategoriaRequest;
import danieldjgomes.larica.core.categoria.dtos.CriarCategoriaResponse;
import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriarCategoriaUseCaseImpl implements CriarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;
    private final GerarUUIDUseCase gerarUUIDUseCase;
    private final CategoriaMapper categoriaMapper;

    public CriarCategoriaResponse criar(CriarCategoriaRequest criarCategoriaRequest) {
        CategoriaEntity categoria = categoriaMapper.toEntity(criarCategoriaRequest);
        categoria.setId(gerarUUIDUseCase.gerar());
        CategoriaEntity persistCategoria = categoriaPersist.createCategoria(categoria);
        return categoriaMapper.toCriarCategoriaResponse(persistCategoria);

    }

}
