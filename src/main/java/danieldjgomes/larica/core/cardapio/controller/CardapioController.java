package danieldjgomes.larica.core.cardapio.controller;

import danieldjgomes.larica.core.cardapio.dtos.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.CardapioResponseDTO;
import danieldjgomes.larica.core.usecases.CardapioUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cardapio")
@AllArgsConstructor
public class CardapioController {

    private final CardapioUseCase cardapioUseCase;


    @PostMapping("/{pratoId}/create/{tipoCulinariaId}")
    public ResponseEntity<CardapioResponseDTO> createCardapio(@PathVariable UUID pratoId, @PathVariable UUID tipoCulinariaId) {
        CardapioResponseDTO createdCardapio = cardapioUseCase.createCardapio(pratoId, tipoCulinariaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCardapio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardapioResponseDTO> getCardapioById(@PathVariable UUID id) {
        Optional<CardapioResponseDTO> cardapio = cardapioUseCase.getCardapioById(id);
        return cardapio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CardapioResponseDTO>> listAllCardapios() {
        List<CardapioResponseDTO> cardapios = cardapioUseCase.listAllCardapios();
        return new ResponseEntity<>(cardapios, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CardapioResponseDTO> updatePrato(@PathVariable UUID id, @RequestBody @Valid CardapioRequestDTO updatedCardapio) {
        Optional<CardapioResponseDTO> prato = cardapioUseCase.updateCardapio(id, updatedCardapio);
        return prato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCardapio(@PathVariable UUID id) {
        cardapioUseCase.deleteCardapio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
