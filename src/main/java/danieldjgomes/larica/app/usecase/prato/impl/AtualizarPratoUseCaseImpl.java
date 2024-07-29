package danieldjgomes.larica.app.usecase.prato.impl;

import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.ports.database.PratoPersist;
import danieldjgomes.larica.app.usecase.prato.AtualizarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.reqeust.AtualizarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;
import danieldjgomes.larica.infrastructure.mapper.PratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AtualizarPratoUseCaseImpl implements AtualizarPratoUseCase {

    private final PratoPersist pratoPersist;

    public Optional<PratoResponse> updatePrato(String id, AtualizarPratoRequest pratoRequest) {
        Optional<PratoEntity> pratoAtualizado = pratoPersist.updatePrato(id, pratoRequest);

        return pratoAtualizado.map(PratoMapper.INSTANCE::toAtualizarPratoResponse);
    }

}
