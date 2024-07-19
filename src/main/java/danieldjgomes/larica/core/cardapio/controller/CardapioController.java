package danieldjgomes.larica.core.cardapio.controller;

import danieldjgomes.larica.core.cardapio.dtos.request.*;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
import danieldjgomes.larica.core.usecases.CardapioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cardapio")
@AllArgsConstructor
public class CardapioController {

    private final CardapioUseCase cardapioUseCase;


    @PostMapping
    public ResponseEntity<CardapioResponseDTO> criarCardapio(@RequestBody CardapioRequestDTO dto) {
        CardapioResponseDTO response = cardapioUseCase.criarCardapio(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/adicionar-pratos")
    public ResponseEntity<Void> adicionarPratos(@RequestBody AdicionarPratosRequestDTO dto) {
        cardapioUseCase.adicionarPratos(dto.getCardapioId(), dto.getPratoIds());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/remover-prato")
    public ResponseEntity<Void> removerPrato(@RequestBody RemoverPratoRequestDTO dto) {
        cardapioUseCase.removerPrato(dto.getCardapioId(), dto.getPratoId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardapioResponseDTO> atualizarCardapio(@PathVariable String id, @RequestBody CardapioUpdateRequestDTO dto) {
        CardapioResponseDTO response = cardapioUseCase.atualizarCardapio(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/desativar")
    public ResponseEntity<Void> desativarCardapio(@RequestBody DesativarCardapioRequestDTO dto) {
        cardapioUseCase.desativarCardapio(dto.getCardapioId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
