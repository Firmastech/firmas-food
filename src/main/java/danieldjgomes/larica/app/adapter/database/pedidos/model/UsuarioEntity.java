package danieldjgomes.larica.app.adapter.database.pedidos.model;

import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.infrastructure.Papel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity implements UserDetails {

    @Id
    @UuidGenerator
    @Column(nullable = false)
    private String id;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoEntity> pedidos;

    @OneToOne
    private RestauranteEntity restaurante;

    private String email;
    private String senha;
    private Boolean ativo;
    private String primeiroNome;
    private String segundoNome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_papel",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "papel_id"))
    private Set<Papel> papeis = new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return papeis.stream().
                filter(Papel::getAtivo)
                .flatMap(p -> p.getPermissoes().stream()
                        .map(permissao -> new SimpleGrantedAuthority(permissao.getNome()))).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getAtivo();
    }
}