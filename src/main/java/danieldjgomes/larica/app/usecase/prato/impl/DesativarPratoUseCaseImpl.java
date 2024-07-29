package danieldjgomes.larica.app.usecase.prato.impl;

import danieldjgomes.larica.app.ports.database.PratoPersist;
import danieldjgomes.larica.app.usecase.prato.DesativarPratoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class DesativarPratoUseCaseImpl implements DesativarPratoUseCase {

    private final PratoPersist pratoPersist;

    public void desativar(String pratoId) {
        pratoPersist.disablePrato(pratoId);
    }
}
