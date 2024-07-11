package danieldjgomes.larica.core.cardapio.service.impl;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.cardapio.exception.CardapioNotFoundException;
import danieldjgomes.larica.core.cardapio.repository.CardapioRepository;
import danieldjgomes.larica.core.cardapio.service.CardapioService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardapioServiceImpl implements CardapioService {

    private final CardapioRepository repository;
    private final ModelMapper modelMapper;

    public Cardapio createCardapio(Cardapio cardapio) {
        return repository.save(cardapio);
    }

    public Optional<Cardapio> getCardapioById(Long id) {
        return repository.findById(id);
    }

    public Optional<Cardapio> updateCardapio(Long id, Cardapio updatedCardapio) {
        Cardapio existingCardapio = getExistingCardapio(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(updatedCardapio, existingCardapio);
        return Optional.of(repository.save(existingCardapio));
    }

    public List<Cardapio> listAllCardapios() {
        return repository.findAll();
    }

    public void deleteCardapio(Long id) {
        Cardapio existingCardapio = getExistingCardapio(id);
        repository.deleteById(id);
    }

    private Cardapio getExistingCardapio(Long id) {
        Optional<Cardapio> existingCardapio = this.getCardapioById(id);
        if (existingCardapio.isEmpty()) {
            throw new CardapioNotFoundException("Cardapio not found with id: " + id);
        }
        return existingCardapio.get();
    }


}
