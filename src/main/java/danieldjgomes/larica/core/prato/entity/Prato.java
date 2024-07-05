package danieldjgomes.larica.core.prato.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Prato {

    private UUID id;
    private String nome;
    private String descricao;
    private String urlImagem;
    private BigDecimal preco;
    private String tagDescontoId;
}
