package danieldjgomes.larica.core.prato.service;

import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.prato.entity.Prato;
import danieldjgomes.larica.core.prato.repository.PratoRepository;
import danieldjgomes.larica.core.usecases.PratoUseCase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PratoUseCaseImpl implements PratoUseCase {

    private final PratoRepository pratoRepository;
    private final ModelMapper modelMapper;

    public Prato createPrato(Prato prato) {
        return pratoRepository.save(prato);
    }

    public Optional<Prato> getPratoById(Long id) {
        return pratoRepository.findById(id);
    }

    public Optional<Prato> updatePrato(Long id, Prato updatedPrato) {
        Prato existingPrato = getExistingPrato(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(updatedPrato, existingPrato);
        return Optional.of(pratoRepository.save(existingPrato));
    }

    public List<Prato> listAllPratos() {
        return pratoRepository.findAll();
    }

    public void deletePrato(Long id) {
        Prato existingPrato = getExistingPrato(id);
        pratoRepository.deleteById(id);
    }

    private Prato getExistingPrato(Long id) {
        Optional<Prato> existingPrato = this.getPratoById(id);
        if (existingPrato.isEmpty()) {
            throw new EntityNotFoundException("Prato not found with id: " + id);
        }
        return existingPrato.get();
    }
}
