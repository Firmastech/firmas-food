package danieldjgomes.larica.core.cardapio.controller;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.cardapio.service.CardapioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cardapio")
@AllArgsConstructor
public class CardapioController {

    private final CardapioService cardapioService;


    @GetMapping
    public List<Cardapio> cardapio() {
        List<Cardapio> cardapioList = cardapioService.listAllCardapios();
        return new ResponseEntity<>(cardapioList, HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    public Optional<Cardapio> getCardapioById(@PathVariable Long id) {
        Optional<Cardapio> cardapio = cardapioService.getCardapioById(id);
        return cardapio;
    }

    @PostMapping("/create")
    public ResponseEntity<Cardapio> createCardapio(@RequestBody @Valid Cardapio cardapio) {
        Cardapio createdCardapio = cardapioService.createCardapio(cardapio);
        return new ResponseEntity<>(createdCardapio, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Cardapio>> updateCardapio(@PathVariable Long id, @RequestBody @Valid Cardapio cardapio) {
        Optional<Cardapio> updatedCardapio = cardapioService.updateCardapio(id, cardapio);
        return new ResponseEntity<>(updatedCardapio, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCardapio(@PathVariable Long id) {
        cardapioService.deleteCardapio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
