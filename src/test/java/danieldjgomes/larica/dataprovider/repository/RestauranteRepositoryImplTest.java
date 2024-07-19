//package danieldjgomes.larica.dataprovider.repository;
//
//import danieldjgomes.larica.core.restaurante.entity.Restaurante;
//import danieldjgomes.larica.dataprovider.repository.entity.RestauranteEntity;
//import danieldjgomes.larica.dataprovider.repository.impl.RestauranteRepositoryImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class RestauranteRepositoryImplTest {
//
//    @Mock
//    private RestauranteDao restauranteDao;
//
//    @Mock
//    private RestauranteMapper restauranteMapper;
//
//    @InjectMocks
//    private RestauranteRepositoryImpl restauranteRepository;
//
//    @Test
//    void save() {
//        Restaurante restaurante = new Restaurante();
//        RestauranteEntity entity = new RestauranteEntity();
//
////        when(RestauranteMapper..from(restaurante)).thenReturn(entity);
//
//        restauranteRepository.save(restaurante);
//
//       // verify(restauranteMapper).from(restaurante);
//        verify(restauranteDao).save(entity);
//    }
//}