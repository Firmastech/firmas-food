package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.ports.database.CardapioPratoPersist;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import danieldjgomes.larica.app.usecase.cardapioPrato.AddPratosToCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapioPrato.BuscarCardapioByIdUseCase;
import danieldjgomes.larica.app.usecase.cardapioPrato.RemovePratoFromCardapioUseCase;
import danieldjgomes.larica.app.usecase.cardapioPrato.request.AddPratosToCardapioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prato-cardapio")
@RequiredArgsConstructor
public class CardapioPratoController {

    private final AddPratosToCardapioUseCase addPratosToCardapio;
    private final BuscarCardapioByIdUseCase buscarCardapioByIdUseCase;
    private final RemovePratoFromCardapioUseCase removePratosFromCardapio;

    @PostMapping("/{cardapioId}")
    public ResponseEntity<AddPratosToCardapioRequest> addPratosToCardapio(
            @PathVariable String cardapioId,
            @RequestBody AddPratosToCardapioRequest request) {
        addPratosToCardapio.addPratosToCardapio(cardapioId, request.getPratoIds());
        return ResponseEntity.ok(request);
    }

    @GetMapping("/{cardapioId}")
    public ResponseEntity<CardapioResponse> getCardapioById(@PathVariable String cardapioId) {
        CardapioResponse response = buscarCardapioByIdUseCase.buscarCardapioById(cardapioId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{cardapioId}")
    public ResponseEntity<Void> removePratosFromCardapio(@PathVariable String cardapioId, @RequestBody List<String> pratoIds) {
        removePratosFromCardapio.desativar(cardapioId, pratoIds);
        return ResponseEntity.noContent().build();
    }
}
