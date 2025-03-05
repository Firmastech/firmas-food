package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.usecase.cardapio.*;
import danieldjgomes.larica.app.usecase.cardapio.request.AtualizarDescritivosCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.request.CriarCardapioRequest;
import danieldjgomes.larica.app.usecase.cardapio.response.AtualizarCardapioResponse;
import danieldjgomes.larica.app.usecase.cardapio.response.CardapioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rest/cardapios")
@RequiredArgsConstructor
public class CardapioController {
    private final CriarCardapioUseCase criarCardapioUseCase;
    private final AtualizarDescritivoCardapioUseCase atualizarDescritivoCardapioUseCase;
    private final BuscarTodosCardapioUseCase buscarTodosCardapioUseCase;
    private final BuscarCardapioAtivoUseCase buscarCardapioAtivoUseCase;
    private final DesabilitarCardapioUseCase desabilitarCardapioUseCase;

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

    @GetMapping("/ativo")
    public ResponseEntity<CardapioResponse> buscarCardapioAtivo() {
        return buscarCardapioAtivoUseCase
                .buscar()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<CardapioResponse>> buscarTodosCardapios(Pageable pageable) {
        return ResponseEntity.ok(buscarTodosCardapioUseCase
                .buscarTodosCardapios(pageable));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity desabilitar(@PathVariable String id) {
        desabilitarCardapioUseCase.desabilitar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
