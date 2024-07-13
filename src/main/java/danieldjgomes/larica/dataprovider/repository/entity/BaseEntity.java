package danieldjgomes.larica.dataprovider.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "data_inclusao")
    private LocalDateTime dataInclusao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_exclusao")
    private LocalDateTime dataExclusao;

    @Column(name = "ativo", nullable = false, length = 10)
    private Boolean isActive;
}
