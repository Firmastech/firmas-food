package danieldjgomes.larica.app.adapter.database.pedidos.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_pedido")
public class ItemPedidoEntity {

    @Id
    @Column(length = 36, nullable = false)
    private String id;

    @Column(name = "prato_id", length = 36, nullable = false)
    private String pratoId;

    @Column(length = 600, nullable = false)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
    private PedidoEntity pedido;

    @Column(nullable = false)
    private int quantidade;
}