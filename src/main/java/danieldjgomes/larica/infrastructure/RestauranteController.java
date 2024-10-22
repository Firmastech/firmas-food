package danieldjgomes.larica.infrastructure;

import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.RegistrarRestauranteUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping(path = "api/restaurantes")
public class RestauranteController {

    private final RegistrarRestauranteUseCase registrarRestauranteInterador;
    private DTOMapper mapper;

    @PostMapping
    public ResponseEntity criarRestaurante(@RequestBody @Valid CriarRestauranteDTO criarRestauranteDTO) {
        Restaurante restaurante = mapper.toRestaurante(criarRestauranteDTO);
        registrarRestauranteInterador.registrarRestaurante(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}
