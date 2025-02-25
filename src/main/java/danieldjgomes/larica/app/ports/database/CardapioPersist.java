package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.core.categoria.entity.CategoriaEntity;

import java.util.List;
import java.util.Optional;

public interface CardapioPersist {

    CardapioEntity criar(CardapioEntity cardapio);

    CardapioEntity atualizarDescritivos(CardapioEntity cardapio);
    List<CardapioEntity> buscarCardapios();

    Optional<CardapioEntity> buscarDetalheCardapio(String cardapioId, String restauranteId);

    void desativarCardapio(String cardapioId, String restauranteId);

    Optional<CardapioEntity> buscarCardapioAtivo(String id);

    void adicionarCategorias(CardapioEntity cardapioEntity, List<CategoriaEntity> categorias);
}
