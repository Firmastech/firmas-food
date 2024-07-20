package danieldjgomes.larica.usecase.restaurante.request;

import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AtualizarRestauranteRequestDTO {
    @NotBlank
    @NotNull
    private String id;
    private String nome;
    private Integer tempoEstimadoDeEntrega;
    private StatusFuncionamento statusFuncionamento;
}
