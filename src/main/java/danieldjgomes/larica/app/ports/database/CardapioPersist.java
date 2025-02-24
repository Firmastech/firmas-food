package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;

import java.util.List;
import java.util.Optional;

public interface CardapioPersist {

    CardapioEntity criar(CardapioEntity cardapio);

    CardapioEntity atualizarDescritivos(CardapioEntity cardapio);
    List<CardapioEntity> buscarCardapios();

    Optional<CardapioEntity> buscarDetalheCardapio(String cardapioId);

    void desativarCardapio(String cardapio);

}
