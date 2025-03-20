package danieldjgomes.larica.app.adapter.controller;

import danieldjgomes.larica.app.adapter.database.pedidos.model.UsuarioEntity;
import danieldjgomes.larica.app.usecase.prato.AtualizarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.BuscarPratosUseCase;
import danieldjgomes.larica.app.usecase.prato.CriarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.DesativarPratoUseCase;
import danieldjgomes.larica.app.usecase.prato.reqeust.AtualizarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.reqeust.CriarPratoRequest;
import danieldjgomes.larica.app.usecase.prato.response.AtualizarPratoResponse;
import danieldjgomes.larica.app.usecase.prato.response.CriarPratoResponse;
import danieldjgomes.larica.app.usecase.prato.response.PratoResponse;
import danieldjgomes.larica.infrastructure.AuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/pratos")
@RequiredArgsConstructor
public class PratosController {

    private final CriarPratoUseCase criarPratoUseCase;
    private final BuscarPratosUseCase buscarPratosUseCase;
    private final AtualizarPratoUseCase atualizarPratoUseCase;
    private final DesativarPratoUseCase desativarPratoUseCase;

    @PostMapping()
    @PreAuthorize("hasAuthority('CRIAR_PRATO')")
    public ResponseEntity<CriarPratoResponse> criarPrato(@RequestBody @Valid CriarPratoRequest request) {
        CriarPratoResponse createdPrato = criarPratoUseCase.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrato);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_PRATOS')")
    public ResponseEntity<PratoResponse> buscarPratoPorId(@PathVariable String id) {
        PratoResponse prato = buscarPratosUseCase.buscarPratoPorId(id);
        return ResponseEntity.ok(prato);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VER_PRATOS')")
    public ResponseEntity<List<PratoResponse>> buscarPratos() {
        UsuarioEntity usuario = AuthorizationService.findUsuario();
        List<PratoResponse> pratos = buscarPratosUseCase.buscarPratosPorRestauranteId(usuario.getRestaurante().getId());
        return new ResponseEntity<>(pratos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_PRATO')")
    public ResponseEntity<AtualizarPratoResponse> atualizarPratos(@PathVariable String id, @RequestBody AtualizarPratoRequest updatedPrato) {
        AtualizarPratoResponse pratoResponse = atualizarPratoUseCase.atualizarPratos(id, updatedPrato);
        return new ResponseEntity<>(pratoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DESABILITAR_PRATO')")
    public ResponseEntity<Void> desativarPrato(@PathVariable String id) {
        desativarPratoUseCase.desativar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
