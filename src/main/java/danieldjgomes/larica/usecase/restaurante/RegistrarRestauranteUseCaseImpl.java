package danieldjgomes.larica.usecase.restaurante;

import danieldjgomes.larica.adapter.database.contato.model.ContatoModel;
import danieldjgomes.larica.adapter.database.restaurante.model.RestauranteModel;
import danieldjgomes.larica.core.endereco.entity.Endereco;
import danieldjgomes.larica.core.usecases.contato.CadastrarContatoUseCase;
import danieldjgomes.larica.core.usecases.endereco.CadastrarEnderecoUseCase;
import danieldjgomes.larica.ports.database.RestaurantePersist;
import danieldjgomes.larica.core.restaurante.entity.Restaurante;
import danieldjgomes.larica.core.usecases.restaurante.RegistrarRestauranteUseCase;
import danieldjgomes.larica.adapter.mapper.RestauranteMapper;
import danieldjgomes.larica.usecase.restaurante.exceptions.RestauranteNomeInvalidoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Component
@AllArgsConstructor
public class RegistrarRestauranteUseCaseImpl implements RegistrarRestauranteUseCase {

    private final RestaurantePersist restaurantePersist;
    private final CadastrarEnderecoUseCase cadastrarEnderecoUseCase;
    private final CadastrarContatoUseCase cadastrarContatoUseCase;
    private final RestauranteMapper mapper;

    public Restaurante registrarRestaurante(final Restaurante restaurante) {
        Endereco endereco = restaurante.getEndereco();
        List<ContatoModel> contatoModels = restaurante.getContatoModels();

        restaurante.setId(UUID.randomUUID().toString());

        restaurantePersist.findByNome(restaurante.getNome())
                .ifPresent((nome)->{
                    throw new RestauranteNomeInvalidoException("Nome já está em uso");
                });

        Endereco enderecoModel = cadastrarEnderecoUseCase.cadastrar(endereco);
        RestauranteModel restauranteModel = mapper.toEntity(restaurante);
        restauranteModel.setEnderecoId(enderecoModel.getId());
        restauranteModel = restaurantePersist.save(restauranteModel);
        cadastrarContatoUseCase.cadastrar(contatoModels, restaurante.getId());
        return mapper.toRestaurante(restauranteModel);
    }

}
