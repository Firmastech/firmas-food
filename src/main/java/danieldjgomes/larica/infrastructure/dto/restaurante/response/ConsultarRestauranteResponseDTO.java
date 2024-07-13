package danieldjgomes.larica.infrastructure.dto.restaurante.response;

import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;

public class ConsultarRestauranteResponseDTO {

    private String id;
    private String nome;
    private Integer tempoEstimadoDeEntrega;
    private StatusFuncionamento statusFuncionamento;
    //private Endereco endereco;
    //private Cardapio cardapio;
}
