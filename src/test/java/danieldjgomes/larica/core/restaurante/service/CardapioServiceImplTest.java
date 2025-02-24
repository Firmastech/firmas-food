//package danieldjgomes.larica.core.restaurante.service;
//
//import danieldjgomes.larica.core.cardapio.entity.Cardapio;
//import danieldjgomes.larica.core.cardapio.repository.CardapioRepository;
//import danieldjgomes.larica.core.cardapio.service.CardapioUseCaseImpl;
//import danieldjgomes.larica.core.culinaria.entity.Culinaria;
//import danieldjgomes.larica.core.culinaria.repository.CulinariaRepository;
//import danieldjgomes.larica.core.exception.EntityNotFoundException;
//import danieldjgomes.larica.app.adapter.database.prato.model.Prato;
//import danieldjgomes.larica.core.prato.repository.PratoRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//public class CardapioServiceImplTest {
//
//    @Mock
//    private CardapioRepository cardapioRepository;
//
//    @Mock
//    private PratoRepository pratoRepository;
//
//    @Mock
//    private CulinariaRepository culinariaRepository;
//
//    @InjectMocks
//    private CardapioUseCaseImpl cardapioUseCase;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateCardapioNotFound() {
//        UUID pratoId = UUID.randomUUID();
//        UUID tipoCulinariaId = UUID.randomUUID();
//
//        Prato prato = new Prato();
//        prato.setId(pratoId);
//        when(pratoRepository.findById(pratoId))
//                .thenReturn(any());
//
//        Culinaria culinaria = new Culinaria();
//        culinaria.setCulinariaId(tipoCulinariaId);
//        when(culinariaRepository.findById(tipoCulinariaId))
//                .thenReturn(any());
//
//        Cardapio cardapio = new Cardapio();
//        cardapio.setPratoId(prato);
//        cardapio.setTipoCulinariaId(culinaria);
//        when(cardapioRepository.save(cardapio))
//                .thenReturn(any());
//
//        when(cardapioRepository.findById(pratoId))
//                .thenThrow(new EntityNotFoundException(Cardapio.class.getName()));
//
//        try {
//            cardapioRepository.save(cardapio);
//        } catch (Exception e) {
//            assertEquals(EntityNotFoundException.class, e.getClass());
//            assertEquals("Cardapio not found with id", e.getMessage());
//        }
//
//    }
//
//    @Test
//    public void testCreateCardapio() {
//
//        UUID pratoId = UUID.randomUUID();
//        UUID tipoCulinariaId = UUID.randomUUID();
//        Prato prato = new Prato();
//
//    }
//}
//
////void whenFindByIdThanReturnAnObjectNotFoundException(){
////    when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
////
////    try{
////        userService.findById(ID);
////    }catch (Exception ex){
////        assertEquals(ObjectNotFoundException.class, ex.getClass());
////        assertEquals("Objeto não encontrado", ex.getMessage());
////    }