package danieldjgomes.larica.adapter.database.pedidos.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID usuarioId;

    @Column(nullable = false)
    private UUID restauranteId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    @Builder.Default()
    private List<ItemPedidoEntityList> itens = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private String status;

    @CreationTimestamp
    private LocalDateTime dataHoraPedido;

    @UpdateTimestamp
    private LocalDateTime dataHoraEntrega;
}
