package danieldjgomes.larica.core.endereco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String id;
    private String rua;
    private Integer numero;
    private String cep;
    private String cidade;
    private String uf;
    private String pontoReferencia;
}
