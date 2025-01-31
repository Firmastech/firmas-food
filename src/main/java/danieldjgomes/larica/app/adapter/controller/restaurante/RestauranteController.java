package danieldjgomes.larica.app.adapter.controller.restaurante;

import danieldjgomes.larica.app.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.app.usecase.restaurante.response.Restaurante;
import danieldjgomes.larica.app.usecase.restaurante.port.AtualizarRestauranteUseCase;
import danieldjgomes.larica.app.usecase.restaurante.port.ConsultarRestauranteUseCase;
import danieldjgomes.larica.app.usecase.restaurante.port.InativarRestauranteUseCase;
import danieldjgomes.larica.app.usecase.restaurante.port.RegistrarRestauranteUseCase;
import danieldjgomes.larica.app.usecase.restaurante.request.AtualizarRestauranteRequest;
import danieldjgomes.larica.app.usecase.restaurante.request.CriarRestauranteRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping(path = "api/restaurantes")
public class RestauranteController {

    private final RegistrarRestauranteUseCase registrarRestauranteInterador;
    private final ConsultarRestauranteUseCase consultarRestauranteUseCase;
    private final AtualizarRestauranteUseCase atualizarRestauranteUseCase;
    private final InativarRestauranteUseCase inativarRestauranteUseCase;
    private final RestauranteMapper mapper;

    @PostMapping
    public ResponseEntity registrarRestaurante(@RequestBody @Valid CriarRestauranteRequest dto) {
        Restaurante restaurante = mapper.toRestaurante(dto);
        Restaurante restauranteCriado = registrarRestauranteInterador.registrarRestaurante(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteCriado);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> consultarRestaurante(@PathVariable @NotBlank @NotNull String id) {
        return ResponseEntity.ok(consultarRestauranteUseCase.consultar(id));
    }

    @PutMapping
    public ResponseEntity atualizarRestaurante(@RequestBody @Valid AtualizarRestauranteRequest dto) {
        Restaurante restaurante = mapper.toRestaurante(dto);
        atualizarRestauranteUseCase.update(restaurante);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity inativarRestaurante(@PathVariable @NotBlank @NotNull String id) {
        inativarRestauranteUseCase.inativarRestaurante(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
