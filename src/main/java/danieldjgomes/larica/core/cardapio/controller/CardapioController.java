package danieldjgomes.larica.core.cardapio.controller;

import danieldjgomes.larica.core.cardapio.dtos.request.CardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.request.CardapioUpdateRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.request.DesativarCardapioRequestDTO;
import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
import danieldjgomes.larica.core.usecases.CardapioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cardapio")
@RequiredArgsConstructor
public class CardapioController {

    private final CardapioUseCase cardapioUseCase;

    @PostMapping
    public ResponseEntity<CardapioResponseDTO> criarCardapio(@RequestBody CardapioRequestDTO dto) {
        CardapioResponseDTO response = cardapioUseCase.criarCardapio(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
