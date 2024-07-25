package danieldjgomes.larica.usecase.restaurante;


import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.core.usecases.endereco.ConsultarEnderecoPorCepNumeroUseCase;
import danieldjgomes.larica.ports.database.RestaurantePersist;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.restaurante.RegistrarRestauranteUseCase;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.usecase.endereco.exceptions.EnderecoInvalidoException;
import danieldjgomes.larica.usecase.restaurante.exceptions.RestauranteNomeInvalidoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RegistrarRestauranteUseCaseImpl implements RegistrarRestauranteUseCase {

    private final RestaurantePersist restaurantePersist;
    private final ConsultarEnderecoPorCepNumeroUseCase consultarEnderecoPorCepNumeroUseCase;
    private final RestauranteMapper mapper;

    public Restaurante registrarRestaurante(final Restaurante restaurante) {
        final String cep = restaurante.getEndereco().getCep();
        final String numero = restaurante.getEndereco().getNumero();

        Optional.ofNullable(consultarEnderecoPorCepNumeroUseCase.consultar(cep,numero))
                .ifPresent((endereco)->{
                   throw new EnderecoInvalidoException("Endereco Já Utilizado");
                });

        restaurantePersist.findByNome(restaurante.getNome())
                .ifPresent((nome)->{
                    throw new RestauranteNomeInvalidoException("Nome já Utilizado");
                });

        restaurante.setId(UUID.randomUUID().toString());
        restaurante.getEndereco().setId(UUID.randomUUID().toString());
        RestauranteModel restauranteModel = mapper.toEntity(restaurante);
        restauranteModel = restaurantePersist.save(restauranteModel);
        return mapper.toRestaurante(restauranteModel);
    }

}
