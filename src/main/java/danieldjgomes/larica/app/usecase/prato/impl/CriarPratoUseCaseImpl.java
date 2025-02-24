package danieldjgomes.larica.app.usecase.prato.impl;

import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.ports.database.PratoPersist;
import danieldjgomes.larica.app.usecase.prato.CriarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.reqeust.CriarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.response.CriarPratoResponse;
import danieldjgomes.larica.infrastructure.mapper.PratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CriarPratoUseCaseImpl implements CriarPratoUseCase {

    private final PratoPersist pratoPersist;
    private final PratoMapper pratoMapper;

    public CriarPratoResponse criar(CriarPratoRequest criarPratoRequest) {
        PratoEntity prato = pratoMapper.toEntity(criarPratoRequest);
        PratoEntity pratoSalvo = pratoPersist.criarPrato(prato);
        return pratoMapper.toCriarPratoResponse(pratoSalvo);
    }

}
