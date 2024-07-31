package danieldjgomes.larica.app.usecase.prato.impl;

import danieldjgomes.larica.app.ports.database.PratoPersist;
import danieldjgomes.larica.app.usecase.prato.AtualizarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.BuscarPratosUseCase;
import danieldjgomes.larica.app.usecase.prato.exception.PratoNotFoundException;
import danieldjgomes.larica.app.usecase.prato.reqeust.AtualizarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.response.AtualizarPratoResponse;
import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;
import danieldjgomes.larica.infrastructure.mapper.PratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarPratoUseCaseImpl implements AtualizarPratoUseCase {

    private final PratoPersist pratoPersist;
    private final BuscarPratosUseCase buscarPratosUseCase;

    public AtualizarPratoResponse updatePrato(String id, AtualizarPratoRequest pratoRequest) {
        Integer pratoAtualizado = pratoPersist.updatePrato(id, pratoRequest);
        if (pratoAtualizado >= 1) {
            PratoResponse pratoResponse = buscarPratosUseCase.buscarPrato(id);
            return PratoMapper.INSTANCE.toAtualizarPratoResponse(pratoResponse);
        }
        throw new PratoNotFoundException();
    }

}
