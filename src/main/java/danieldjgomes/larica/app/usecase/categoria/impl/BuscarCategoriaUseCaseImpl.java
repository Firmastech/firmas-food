package danieldjgomes.larica.app.usecase.categoria.impl;

import danieldjgomes.larica.app.ports.database.CategoriaPersist;
import danieldjgomes.larica.app.usecase.cardapio.BuscarDetalheCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.app.usecase.categoria.BuscarCategoriaUseCase;
import danieldjgomes.larica.app.usecase.categoria.exception.CategoriaNotFoundException;
import danieldjgomes.larica.app.usecase.categoria.response.BuscarDetalhesCategoriaResponse;
import danieldjgomes.larica.app.usecase.categoria.response.CategoriaResponse;
import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import danieldjgomes.larica.infrastructure.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarCategoriaUseCaseImpl implements BuscarCategoriaUseCase {

    private final CategoriaPersist categoriaPersist;

    private final BuscarDetalheCardapioUseCase buscarDetalheCardapioUseCase;
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaResponse> buscarCategorias(String cardapioId) {

        validarCardapio(cardapioId);

        List<CategoriaEntity> categoria = categoriaPersist.buscarCategorias(cardapioId);
        return categoria
                .stream()
                .map(categoriaMapper::toResponseDTO)
                .toList();
    }

    public BuscarDetalhesCategoriaResponse buscarDetalhesCategoria(String categoriaId, String cardapioId) {

        validarCardapio(cardapioId);

        CategoriaEntity categoria = categoriaPersist.buscarDetalhesCategoria(categoriaId)
                .orElseThrow(CategoriaNotFoundException::new);

        return categoriaMapper.toDetalhesCategoria(categoria);
    }

    private void validarCardapio(String cardapioId) {
        if (buscarDetalheCardapioUseCase.buscarDetalheCardapio(cardapioId).isEmpty()) {
            throw new CardapioNotFoundException();
        }
    }

}
