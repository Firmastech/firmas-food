package danieldjgomes.larica.app.adapter.database.prato.impl;

import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.adapter.database.prato.repository.PratoRepository;
import danieldjgomes.larica.app.ports.database.PratoPersist;
import danieldjgomes.larica.app.usecase.prato.exception.PratoNotFoundException;
import danieldjgomes.larica.app.usecase.prato.reqeust.AtualizarPratoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PratoPersistImpl implements PratoPersist {

    private final PratoRepository pratoRepository;

    @Override
    public PratoEntity createPrato(PratoEntity prato) {
        Date dateAtual = new Date();
        prato.setCriado(dateAtual);
        prato.setAtualizado(dateAtual);
        return pratoRepository.save(prato);
    }

    @Override
    public List<PratoEntity> getAllPratos() {
        return pratoRepository.findAllAtivos();
    }

    @Override
    public Optional<PratoEntity> findPratoById(String id) {
        return pratoRepository.findPratoAtivoById(id);
    }

    @Override
    public Optional<PratoEntity> updatePrato(String id, AtualizarPratoRequest pratoRequest) {
        Integer updatedRows = pratoRepository.atualizarPrato(
                id,
                pratoRequest.getNome(),
                pratoRequest.getDescricao(),
                pratoRequest.getPreco(),
                pratoRequest.getCategoria()
        );

        if (updatedRows > 0) {
            return Optional.ofNullable(pratoRepository.findPratoAtivoById(id).orElseThrow(PratoNotFoundException::new));
        }

        throw new PratoNotFoundException();
    }

    @Override
    public void disablePrato(String id) {
        pratoRepository.desativarPrato(id, new Date());
    }

}
