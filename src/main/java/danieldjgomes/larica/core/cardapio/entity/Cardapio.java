package danieldjgomes.larica.core.cardapio.entity;

import danieldjgomes.larica.core.prato.entity.Prato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cardapio")
public class Cardapio {

    @Id
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", length = 8000)
    private String descricao;

    @Column(name = "restaurante_id", nullable = false)
    private String restauranteId;

    @Column(name = "criado", nullable = false)
    private LocalDateTime criado;

    @Column(name = "atualizado", nullable = false)
    private LocalDateTime atualizado;

    @Column(name = "esta_ativo", nullable = false)
    private boolean estaAtivo;

    @Column(name = "deletado")
    private LocalDateTime deletado;

    @ManyToMany
    @JoinTable(
            name = "cardapio_prato",
            joinColumns = @JoinColumn(name = "cardapio_id"),
            inverseJoinColumns = @JoinColumn(name = "prato_id"))
    private Set<Prato> pratos = new HashSet<>();

}
