package danieldjgomes.larica.app.ports.database;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.categoria.model.CategoriaEntity;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface CardapioPersist {

    CardapioEntity criar(CardapioEntity cardapio);

    CardapioEntity atualizarDescritivos(CardapioEntity cardapio);
    List<CardapioEntity> buscarCardapios();

    Page<CardapioEntity> buscarTodosCardapios(String restauranteId, Pageable pageable);
    Optional<CardapioEntity> buscarCardapioPorId(String restauranteId, String cardapioId);

    void desabilitarCardapio(CardapioEntity cardapioEntity);

    Optional<CardapioEntity> buscarCardapioAtivo(String restauranteId);

    void adicionarCategorias(CardapioEntity cardapioEntity, List<CategoriaEntity> categorias);
}
