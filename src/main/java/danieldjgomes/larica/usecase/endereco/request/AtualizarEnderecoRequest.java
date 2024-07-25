package danieldjgomes.larica.usecase.endereco.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class AtualizarEnderecoRequest {

    @NotBlank
    private String id;
    private String rua;
    private String numero;

    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    private String cep;

    @NotBlank
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;

    private String pontoReferencia;

}
