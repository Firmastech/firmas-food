package danieldjgomes.larica.app.usecase.restaurante;


import danieldjgomes.larica.app.adapter.database.restaurante.model.RestauranteEntity;
import danieldjgomes.larica.app.usecase.endereco.port.ConsultarEnderecoPorCepNumeroUseCase;
import danieldjgomes.larica.app.ports.database.RestaurantePersist;
import danieldjgomes.larica.app.usecase.restaurante.response.Restaurante;
import danieldjgomes.larica.app.usecase.restaurante.port.RegistrarRestauranteUseCase;
import danieldjgomes.larica.app.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.app.usecase.endereco.exceptions.EnderecoInvalidoException;
import danieldjgomes.larica.app.usecase.restaurante.exceptions.RestauranteNomeUtilizadoException;
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
                    throw new RestauranteNomeUtilizadoException();
                });

        restaurante.setId(UUID.randomUUID().toString());
        restaurante.getEndereco().setId(UUID.randomUUID().toString());
        RestauranteEntity restauranteEntity = mapper.toEntity(restaurante);
        restauranteEntity = restaurantePersist.save(restauranteEntity);
        return mapper.toRestaurante(restauranteEntity);
    }

}
