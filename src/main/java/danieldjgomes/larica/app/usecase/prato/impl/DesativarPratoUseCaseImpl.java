package danieldjgomes.larica.app.usecase.prato.impl;

import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.ports.database.PratoPersist;
import danieldjgomes.larica.app.usecase.prato.BuscarPratosUseCase;
import danieldjgomes.larica.app.usecase.prato.DesativarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.exception.PratoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DesativarPratoUseCaseImpl implements DesativarPratoUseCase {

    private final PratoPersist pratoPersist;
    private final BuscarPratosUseCase buscarPratosUseCase;

    public void desativar(String pratoId) {
        Optional<PratoEntity> pratoBuscado = pratoPersist.findPratoById(pratoId);
        if (pratoBuscado.isEmpty()) {
            throw new PratoNotFoundException();
        }

        PratoEntity prato = pratoBuscado.get();
        prato.setDeletado(LocalDateTime.now());
        prato.setAtivo(false);
        pratoPersist.desativarPrato(prato);
    }
}
