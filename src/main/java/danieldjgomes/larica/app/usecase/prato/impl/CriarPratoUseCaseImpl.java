package danieldjgomes.larica.app.usecase.prato.impl;

import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.ports.database.PratoPersist;
import danieldjgomes.larica.app.usecase.GerarUUIDUseCase;
import danieldjgomes.larica.app.usecase.prato.CriarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.reqeust.CriarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;
import danieldjgomes.larica.infrastructure.mapper.PratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CriarPratoUseCaseImpl implements CriarPratoUseCase {

    private final PratoPersist pratoPersist;
    private final GerarUUIDUseCase gerarUUIDUseCase;

    public PratoResponse criar(CriarPratoRequest criarPratoRequest) {
        PratoEntity prato = PratoMapper.INSTANCE.toEntity(criarPratoRequest);
        prato.setId(gerarUUIDUseCase.gerar());
        prato = pratoPersist.createPrato(prato);
        return PratoMapper.INSTANCE.criarPratoResponse(prato);
    }

}
