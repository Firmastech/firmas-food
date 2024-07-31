package danieldjgomes.larica.app.usecase.prato.impl;

import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.ports.database.PratoPersist;
import danieldjgomes.larica.app.usecase.prato.BuscarPratosUseCase;
import danieldjgomes.larica.app.usecase.prato.exception.PratoNotFoundException;
import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;
import danieldjgomes.larica.infrastructure.mapper.PratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BuscarPratoUseCaseImpl implements BuscarPratosUseCase {

    private final PratoPersist pratoPersist;

    public List<PratoResponse> buscarPratoList() {
        List<PratoEntity> prato = pratoPersist.getAllPratos();
        return prato
                .stream()
                .map(PratoMapper.INSTANCE::toPratoResponse)
                .toList();
    }

    public PratoResponse buscarPrato(String pratoId) {
        PratoEntity prato = pratoPersist.findPratoById(pratoId)
                .orElseThrow(PratoNotFoundException::new);
        return PratoMapper.INSTANCE.toPratoResponse(prato);
    }
}
