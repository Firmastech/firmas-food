package danieldjgomes.larica.app.usecase.restaurante.request;

import danieldjgomes.larica.app.usecase.restaurante.enums.StatusFuncionamento;
import danieldjgomes.larica.app.usecase.endereco.request.CadastrarEnderecoRequest;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CriarRestauranteRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Integer tempoEstimadoDeEntrega;

    private StatusFuncionamento statusFuncionamento = StatusFuncionamento.INATIVO;

    @NotNull
    private CadastrarEnderecoRequest endereco;

}
