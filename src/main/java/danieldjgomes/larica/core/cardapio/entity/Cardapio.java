package danieldjgomes.larica.core.cardapio.entity;

import danieldjgomes.larica.core.culinaria.entity.Culinaria;
import danieldjgomes.larica.core.prato.entity.Prato;

import java.util.List;
import java.util.UUID;

public class Cardapio {
    private UUID id;
    private List<Prato> prato;
    private Culinaria culinaria;
}
