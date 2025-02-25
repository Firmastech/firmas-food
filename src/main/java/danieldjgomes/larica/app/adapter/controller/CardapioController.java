package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.usecase.cardapio.*;
import danieldjgomes.larica.app.usecase.cardapio.request.AtualizarDescritivosCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.request.CriarCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rest/cardapios")
@RequiredArgsConstructor
public class CardapioController {
    private final CriarCardapioUseCase criarCardapioUseCase;
    private final AtualizarDescritivoCardapioUseCase atualizarDescritivoCardapioUseCase;
    private final BuscarDetalheCardapioUseCase buscarDetalheCardapioUseCase;
    private final BuscarCardapioAtivoUseCase buscarCardapioAtivoUseCase;
    private final DesativarCardapioUseCase desativarCardapioUseCase;

    @PostMapping
    public ResponseEntity<CardapioResponse> criarCardapio(@RequestBody CriarCardapioRequest request) {
        CardapioResponse response = criarCardapioUseCase.criarCardapio(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtualizarCardapioResponse> atualizarCardapio(@PathVariable String id,
                                                                       @RequestBody AtualizarDescritivosCardapioRequest atualizarDescritivosCardapioRequest) {
        return atualizarDescritivoCardapioUseCase
                .atualizarCardapio(id, atualizarDescritivosCardapioRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<CardapioResponse> buscarCardapioAtivo() {
        return buscarCardapioAtivoUseCase
                .buscar()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //TODO: Analisar se esse deve continuar existindo
    @GetMapping("/{cardapioId}")
    public ResponseEntity<CardapioResponse> buscarDetalheCardapio(@PathVariable String cardapioId) {
        return buscarDetalheCardapioUseCase
                .buscarDetalheCardapio(cardapioId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity desativarCardapio(@PathVariable String id) {
        desativarCardapioUseCase.desativar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
