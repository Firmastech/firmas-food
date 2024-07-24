package danieldjgomes.larica.core.cardapioPrato.controller;

import danieldjgomes.larica.core.cardapio.dtos.response.CardapioResponseDTO;
import danieldjgomes.larica.core.cardapioPrato.request.AddPratosToCardapioRequest;
import danieldjgomes.larica.core.usecases.CardapioPratoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prato-cardapio")
@RequiredArgsConstructor
public class CardapioPratoController {

    private final CardapioPratoUseCase cardapioPratoUseCase;

    @PostMapping("/{cardapioId}")
    public ResponseEntity<AddPratosToCardapioRequest> addPratosToCardapio(
            @PathVariable String cardapioId,
            @RequestBody AddPratosToCardapioRequest request) {
        cardapioPratoUseCase.addPratosToCardapio(cardapioId, request.getPratoIds());
        return ResponseEntity.ok(request);
    }

    @GetMapping("/{cardapioId}")
    public ResponseEntity<CardapioResponseDTO> getCardapioById(@PathVariable String cardapioId) {
        CardapioResponseDTO response = cardapioPratoUseCase.getCardapioById(cardapioId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{cardapioId}")
    public ResponseEntity<Void> removePratosFromCardapio(@PathVariable String cardapioId, @RequestBody List<String> pratoIds) {
        cardapioPratoUseCase.removePratosFromCardapio(cardapioId, pratoIds);
        return ResponseEntity.noContent().build();
    }
}
