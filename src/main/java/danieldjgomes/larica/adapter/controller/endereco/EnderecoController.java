package danieldjgomes.larica.adapter.controller.endereco;

import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.usecases.endereco.AtualizarEnderecoUseCase;
import danieldjgomes.larica.core.usecases.endereco.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.core.usecases.endereco.InativarEnderecoUseCase;
import danieldjgomes.larica.infrastructure.mapper.DTOMapper;
import danieldjgomes.larica.usecase.endereco.request.AtualizarEnderecoRequest;
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
@RequestMapping(path = "api/endereco")
public class EnderecoController {

    private final ConsultarEnderecoPorIdUseCase consultarEnderecoPorIdUseCase;
    private final AtualizarEnderecoUseCase atualizarEnderecoUseCase;
    private final InativarEnderecoUseCase inativarEnderecoUseCase;
    private final DTOMapper mapper;


    @GetMapping("/{id}")
    public ResponseEntity<Endereco> consultarEndereco(@PathVariable @NotBlank @NotNull String id) {
        Endereco endereco = consultarEnderecoPorIdUseCase.consultar(id);
        return ResponseEntity.ok(endereco);
    }

    @PutMapping
    public ResponseEntity atualizarEndereco(@RequestBody @Valid AtualizarEnderecoRequest dto) {
        Endereco endereco = mapper.toEndereco(dto);
        atualizarEnderecoUseCase.update(endereco);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity inativarEndereco(@PathVariable @NotBlank @NotNull String id) {
        inativarEnderecoUseCase.inativar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
