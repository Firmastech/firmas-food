package danieldjgomes.larica.app.adapter.database.prato.impl;

import danieldjgomes.larica.app.adapter.database.prato.model.PratoEntity;
import danieldjgomes.larica.app.adapter.database.prato.repository.PratoRepository;
import danieldjgomes.larica.app.ports.database.PratoPersist;
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
    public Integer updatePrato(String id, AtualizarPratoRequest pratoRequest) {
        return pratoRepository.atualizarPrato(id,
                pratoRequest.getNome(),
                pratoRequest.getDescricao(),
                pratoRequest.getPreco(),
                pratoRequest.getPorcentagemDesconto(),
                pratoRequest.getUrlImagem(),
                pratoRequest.getCategoria());
    }

    @Override
    public void disablePrato(String id) {
        pratoRepository.desativarPrato(id, new Date());
    }

}
