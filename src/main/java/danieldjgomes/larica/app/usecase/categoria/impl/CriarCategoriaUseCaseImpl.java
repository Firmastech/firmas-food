package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.GerarUUIDUseCase;
import danieldjgomes.larica.app.usecase.categoria.CriarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.request.CriarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriarCategoriaUseCaseImpl implements CriarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;
    private final GerarUUIDUseCase gerarUUIDUseCase;

    public CategoriaResponse criar(CriarCategoriaRequest criarCategoriaRequest) {
        CategoriaEntity categoria = CategoriaMapper.INSTANCE.toEntity(criarCategoriaRequest);
        categoria.setId(gerarUUIDUseCase.gerar());
        categoriaPersist.createCategoria(categoria);
        return CategoriaMapper.INSTANCE.toResponse(categoria);

    }

}
