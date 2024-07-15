package danieldjgomes.larica.core.cardapio.service;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.exception.EntityNotFoundException;
import danieldjgomes.larica.core.cardapio.repository.CardapioRepository;
import danieldjgomes.larica.core.usecases.CardapioUseCase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardapioUseCaseImpl implements CardapioUseCase {

    private final CardapioRepository repository;
    private final ModelMapper modelMapper;

    public Cardapio createCardapio(Cardapio cardapio) {
        return repository.save(cardapio);
    }

    public Optional<Cardapio> getCardapioById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Cardapio> updateCardapio(UUID id, Cardapio updatedCardapio) {
        Cardapio existingCardapio = getExistingCardapio(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(updatedCardapio, existingCardapio);
        return Optional.of(repository.save(existingCardapio));
    }

    public List<Cardapio> listAllCardapios() {
        return repository.findAll();
    }

    public void deleteCardapio(UUID id) {
        Cardapio existingCardapio = getExistingCardapio(id);
        repository.deleteById(id);
    }

    private Cardapio getExistingCardapio(UUID id) {
        Optional<Cardapio> existingCardapio = this.getCardapioById(id);
        if (existingCardapio.isEmpty()) {
            throw new EntityNotFoundException("Cardapio not found with id: " + id);
        }
        return existingCardapio.get();
    }


}
