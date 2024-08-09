package danieldjgomes.larica.app.usecase.endereco.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String id;
    private String rua;
    private String numero;
    private String cep;
    private String cidade;
    private String uf;
    private String pontoReferencia;
}
