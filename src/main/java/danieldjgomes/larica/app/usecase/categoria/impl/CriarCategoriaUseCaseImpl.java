package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.GerarUUIDUseCase;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.app.usecase.categoria.CriarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.request.CriarCategoriaRequest;
import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CriarCategoriaUseCaseImpl implements CriarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;
    private final CardapioPersist cardapioPersist;
    private final CategoriaMapper categoriaMapper;

    public CategoriaResponse criar(CriarCategoriaRequest criarCategoriaRequest, String cardapioId) {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        CardapioEntity cardapioEncontrado = buscarCardapio(cardapioId);
        CategoriaEntity categoriaPersistida = criarCategoria(criarCategoriaRequest,usuario.getRestaurante());
        cardapioPersist.adicionarCategorias(cardapioEncontrado, List.of(categoriaPersistida));
        return categoriaMapper.toResponseDTO(categoriaPersistida);

    }

    private CategoriaEntity criarCategoria(CriarCategoriaRequest criarCategoriaRequest, RestauranteEntity restaurante) {
        CategoriaEntity categoria = categoriaMapper.toEntity(criarCategoriaRequest);
        RestauranteEntity restauranteCategoria = new RestauranteEntity();
        restauranteCategoria.setId(restaurante.getId());
        categoria.setRestaurante(restauranteCategoria);
        return categoriaPersist.criarCategoria(categoria);
    }

    private CardapioEntity buscarCardapio(String cardapioId) {
        UsuarioEntity usuario = AuthorizationService.findUsuario();

        Optional<CardapioEntity> cardapioEncontrado = cardapioPersist.buscarCardapioPorId(usuario.getRestaurante().getId(),cardapioId);

        if(cardapioEncontrado.isEmpty()){
            throw new CardapioNotFoundException();
        }
        return cardapioEncontrado.get();
    }

}
