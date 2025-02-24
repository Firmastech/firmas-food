package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.categoria.DesativarCategoriaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DesativarCategoriaUseCaseImpl implements DesativarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;

    public void desativar(String categoraId) {
        categoriaPersist.disableCategoria(categoraId);
    }
}
