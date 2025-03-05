package danieldjgomes.larica.app.usecase.cardapio.impl;

import danieldjgomes.larica.app.adapter.database.cardapio.model.CardapioEntity;
import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.ports.database.CardapioPersist;
import danieldjgomes.larica.app.usecase.cardapio.BuscarCardapioPorIdUseCase;
import danieldjgomes.larica.app.usecase.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import danieldjgomes.larica.infrastructure.mapper.CardapioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class BuscarCardapioPorIdUseCaseImpl implements BuscarCardapioPorIdUseCase {

    private final CardapioPersist cardapioPersist;
    private final CardapioMapper cardapioMapper;

    @Override
    public Optional<CardapioResponse> buscar(String cardapioId) {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        Optional<CardapioEntity> cardapioBuscado = cardapioPersist.buscarCardapioPorId(cardapioId, usuario.getRestaurante().getId());
        return Optional.ofNullable(cardapioBuscado.map(cardapioMapper::toCardapioResponse).orElseThrow(CardapioNotFoundException::new));
    }
}
