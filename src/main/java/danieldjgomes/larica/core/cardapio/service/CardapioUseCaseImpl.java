package danieldjgomes.larica.core.cardapio.service;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.core.cardapio.dtos.request.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.request.CardapioUpdateRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
import danieldjgomes.larica.core.cardapio.entity.CardapioEntity;
import danieldjgomes.larica.core.cardapio.repository.CardapioRepository;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.usecases.CardapioUseCase;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardapioUseCaseImpl implements CardapioUseCase {


    private final CardapioRepository cardapioRepository;

    public CardapioResponseDTO criarCardapio(CardapioRequestDTO cardapioRequestDTO, UsuarioEntity usuario) {
        CardapioEntity cardapioEntity = CardapioMapper.INSTANCE.toEntity(cardapioRequestDTO);
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setId(usuario.getRestaurante().getId());
        cardapioEntity.setRestaurante(restaurante);
        cardapioEntity = cardapioRepository.save(cardapioEntity);
        return CardapioMapper.INSTANCE.toDto(cardapioEntity);
    }

    public CardapioResponseDTO atualizarCardapio(String id, CardapioUpdateRequestDTO cardapioUpdateRequestDTO) {
        CardapioEntity cardapioEntity = getExistingCardapio(id);
        CardapioMapper.INSTANCE.updateCardapioFromDto(cardapioUpdateRequestDTO, cardapioEntity);
        cardapioEntity = cardapioRepository.save(cardapioEntity);
        return CardapioMapper.INSTANCE.toDto(cardapioEntity);
    }

    public void desativarCardapio(String id) {
        CardapioEntity cardapioEntity = getExistingCardapio(id);
        cardapioEntity.setAtivo(false);
        cardapioRepository.save(cardapioEntity);
    }

    private CardapioEntity getExistingCardapio(String id) {
        return cardapioRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cardapio not found with id: " + id));
        //TODO Criar exception especifica para este caso.
    }

    //TODO Criar metodo que liste todos os Cardapios do Restaurante, ativo ou inativo;

    //TODO Criar metodo que seleciona o cardapio ativo por id, por conseguencia ira desativar todos os outros.

}
