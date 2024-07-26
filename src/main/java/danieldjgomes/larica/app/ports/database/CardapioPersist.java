package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioResumidoEntity;

import java.util.List;

public interface CardapioPersist {
    CardapioEntity criar(CardapioEntity cardapio);

    CardapioEntity atualizarDescritivos(String id, String nome, String descricao);

    List<CardapioResumidoEntity> buscarCardapios(String restauranteId);

    CardapioEntity buscarDetalheCardapio(String cardapioId, String restauranteId);

    void desativarCardapio(String cardapio);
}
