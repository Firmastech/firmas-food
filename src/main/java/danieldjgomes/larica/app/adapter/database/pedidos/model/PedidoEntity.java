package danieldjgomes.larica.app.adapter.database.pedidos.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @Column(length = 36, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuarioId;

    @Column(name = "restaurante_id", length = 36, nullable = false)
    private String restauranteId;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default()
    private List<ItemPedidoEntity> itens = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal valor;

    @Column( nullable = false)
    private String status;

    @Column(name = "data_hora_confirmacao")
    private LocalDateTime dataHoraConfirmacao;

    @Column(name = "data_hora_pedido", nullable = false)
    private LocalDateTime dataHoraPedido;

    @Column(name = "data_hora_entrega")
    private LocalDateTime dataHoraEntrega;
}