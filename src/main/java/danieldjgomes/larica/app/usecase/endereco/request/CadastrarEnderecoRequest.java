package danieldjgomes.larica.app.usecase.endereco.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CadastrarEnderecoRequest {

    @NotBlank
    private String rua;

    @NotBlank
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
