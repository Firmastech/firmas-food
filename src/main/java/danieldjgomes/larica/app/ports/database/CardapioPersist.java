package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioResumidoEntity;

import java.util.List;
import java.util.Optional;

public interface CardapioPersist {
    CardapioEntity criar(CardapioEntity cardapio);

    Integer atualizarDescritivos(String id, String nome, String descricao);

    List<CardapioResumidoEntity> buscarCardapios(String restauranteId);

    Optional<CardapioEntity> buscarDetalheCardapio(String cardapioId, String restauranteId);

    void desativarCardapio(String cardapio);

}
