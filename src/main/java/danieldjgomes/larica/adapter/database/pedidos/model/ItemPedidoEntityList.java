package danieldjgomes.larica.adapter.database.pedidos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itens_pedido")
public class ItemPedidoEntityList {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID pratoId;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private int quantidade;
}
