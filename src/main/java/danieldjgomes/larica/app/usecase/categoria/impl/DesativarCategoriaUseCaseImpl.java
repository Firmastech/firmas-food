package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.categoria.DesativarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.exception.CategoriaNotFoundException;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DesativarCategoriaUseCaseImpl implements DesativarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;
    private final CardapioPersist cardapioPersist;

    public void desativar(String categoriaId, String cardapioId) {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        Optional<CardapioEntity> cardapioEntity = cardapioPersist.buscarCardapioPorId(usuario.getRestaurante().getId(), cardapioId);

        buscaCategoria(categoriaId, cardapioEntity)
                .ifPresentOrElse(categoriaPersist::desabilitarCategoria,
                        () -> {
                            throw new CategoriaNotFoundException();
                        }
                );
    }

    private static Optional<CategoriaEntity> buscaCategoria(String categoriaId, Optional<CardapioEntity> cardapioEntity) {
        return cardapioEntity
                .flatMap(cardapio -> cardapio.getCategorias().stream()
                        .filter(cat -> cat.getId().equals(categoriaId))
                        .findFirst()
                );
    }
}
