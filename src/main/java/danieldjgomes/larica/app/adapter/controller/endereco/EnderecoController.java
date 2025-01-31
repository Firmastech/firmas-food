package danieldjgomes.larica.app.adapter.controller.endereco;

import danieldjgomes.larica.app.adapter.mapper.EnderecoMapper;
import danieldjgomes.larica.app.usecase.endereco.response.Endereco;
import danieldjgomes.larica.app.usecase.endereco.port.AtualizarEnderecoUseCase;
import danieldjgomes.larica.app.usecase.endereco.port.ConsultarEnderecoPorIdUseCase;
import danieldjgomes.larica.app.usecase.endereco.port.InativarEnderecoUseCase;
import danieldjgomes.larica.app.usecase.endereco.request.AtualizarEnderecoRequest;
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
@RequestMapping(path = "api/enderecos")
public class EnderecoController {

    private final ConsultarEnderecoPorIdUseCase consultarEnderecoPorIdUseCase;
    private final AtualizarEnderecoUseCase atualizarEnderecoUseCase;
    private final InativarEnderecoUseCase inativarEnderecoUseCase;
    private final EnderecoMapper mapper;


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
