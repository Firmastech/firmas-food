package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.categoria.AtualizarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.exception.CategoriaNotFoundException;
import danieldjgomes.larica.app.usecase.categoria.request.AtualizarCategoriaRequest;
import danieldjgomes.larica.app.usecase.categoria.response.AtualizarCategoriaResponse;
import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AtualizarCategoriaUseCaseImpl implements AtualizarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;
    private final CategoriaMapper categoriaMapper;
    private final CardapioPersist cardapioPersist;

    public Optional<AtualizarCategoriaResponse> updateCategoria(String categoriaId, String cardapioId, AtualizarCategoriaRequest atualizarCategoriaRequest) {

        UsuarioEntity usuario = AuthorizationService.findUsuario();
        Optional<CardapioEntity> cardapioEntity = cardapioPersist.buscarCardapioPorId(usuario.getRestaurante().getId(), cardapioId);

        CategoriaEntity categoriaParaAtualizar = buscaCategoria(categoriaId, cardapioEntity)
                .map(c -> {
                    c.setNome(atualizarCategoriaRequest.getNome());
                    return c;
                })
                .orElseThrow(CategoriaNotFoundException::new);

        Optional<CategoriaEntity> categoriaAtualizada = categoriaPersist.updateCategoria(categoriaParaAtualizar);
        return categoriaAtualizada.map(categoriaMapper::updateEntityFromDTO);


    }

    private static Optional<CategoriaEntity> buscaCategoria(String categoriaId, Optional<CardapioEntity> cardapioEntity) {
        return cardapioEntity
                .flatMap(cardapio -> cardapio.getCategorias().stream()
                        .filter(cat -> cat.getId().equals(categoriaId))
                        .findFirst()
                );
    }
}
