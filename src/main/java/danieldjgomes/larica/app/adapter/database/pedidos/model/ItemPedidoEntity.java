package danieldjgomes.larica.app.adapter.database.pedidos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoEntity {

    @Id
    @Column(length = 36, nullable = false)
    private String id;

    @Column(name = "prato_id", length = 36, nullable = false)
    private String pratoId;

    @Column(name = "observacao", length = 600, nullable = false)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
    private PedidoEntity pedidoId;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
}