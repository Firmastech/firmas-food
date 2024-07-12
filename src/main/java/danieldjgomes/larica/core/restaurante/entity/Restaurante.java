package danieldjgomes.larica.core.restaurante.entity;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.restaurante.entity.enums.StatusFuncionamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante {

    private UUID id;
    private String nome;
    private Integer tempoEstimadoDeEntrega;
    private StatusFuncionamento statusFuncionamento;
    private Endereco endereco;
    private Cardapio cardapio;

  private void validar(Restaurante restaurante){
      List<String> atributosInvalidos = new ArrayList<>();
      validarAtributo(restaurante.getId(),"ID nao pode ser nulo",atributosInvalidos);
      validarAtributo(restaurante.getNome(),"Nome nao pode ser nulo",atributosInvalidos);
      validarAtributo(restaurante.getTempoEstimadoDeEntrega(),"Tempo de entrega nao pode ser nulo",atributosInvalidos);
      validarAtributo(restaurante.getStatusFuncionamento(),"Status de funcionamento nao pode ser nulo",atributosInvalidos);
      notificar(atributosInvalidos);
  }

  private void validarAtributo(final Object atributos,
                                final String message,
                                List<String> invalidList){
      try{
          Objects.requireNonNull(atributos,message);
      }catch (RuntimeException e){
          invalidList.add(e.getMessage());
      }
  }

  private void notificar(final List<String> atributosInvalidos){
    if(!atributosInvalidos.isEmpty()){
       final String message = "Foram encontrado as seguintes inconsistencias: ";
       final String messagefinal = String.join(" ",atributosInvalidos);
       throw new RuntimeException(message + messagefinal);
    }
  }


}
