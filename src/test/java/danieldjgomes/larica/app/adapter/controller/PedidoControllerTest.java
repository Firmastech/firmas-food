package danieldjgomes.larica.app.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import danieldjgomes.larica.app.ports.database.PedidoPersist;
import danieldjgomes.larica.app.usecase.pedido.port.ProcessarPedidoUsecase;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;
import danieldjgomes.larica.util.ObjectBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class PedidoControllerTest {

    @Mock
    private PedidoPersist pedidoPersist;

    @Mock
    private ProcessarPedidoUsecase processarPedidoUsecase;

    @InjectMocks
    private PedidoController pedidoController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedidoController = new PedidoController(pedidoPersist, processarPedidoUsecase);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    void createPedidoSucesso() throws Exception {
        ProcessarPedidoRequest pedidoRequest = ObjectBuilder.buildProcessarPedidoRequestSucesso();

        doNothing().when(pedidoPersist).createPedido(pedidoRequest);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pedidoRequest)))
                .andExpect(status().isOk());

        verify(pedidoPersist, times(1)).createPedido(any());
        verifyNoMoreInteractions(pedidoPersist);
    }

    @Test
    void createPedidoInvalidData() throws Exception {
        ProcessarPedidoRequest pedidoRequest = ObjectBuilder.buildProcessarPedidoRequestSucesso();
        pedidoRequest.setUsuarioId(null);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pedidoRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void processarPedidoSucesso() throws Exception {
        UUID pedidoId = UUID.randomUUID();


        when(pedidoPersist.getPedidoById(pedidoId)).thenReturn(ObjectBuilder.buildProcessarPedidoRequestSucesso());

        mockMvc.perform(post("/pedidos/" + pedidoId + "/processar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(pedidoPersist, times(1)).getPedidoById(pedidoId);
        verify(processarPedidoUsecase, times(1)).processarPedido(any(ProcessarPedidoRequest.class));
        verifyNoMoreInteractions(pedidoPersist, processarPedidoUsecase);
    }

    @Test
    void getPedidoSucesso() throws Exception {
        UUID pedidoId = UUID.randomUUID();
        when(pedidoPersist.getPedidoById(pedidoId)).thenReturn(ObjectBuilder.buildProcessarPedidoRequestSucesso());

        mockMvc.perform(get("/pedidos/" + pedidoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(pedidoPersist, times(1)).getPedidoById(pedidoId);
        verifyNoMoreInteractions(pedidoPersist);
    }

    @Test
    void getPedidoNotFound() throws Exception {
        UUID pedidoId = UUID.randomUUID();
        when(pedidoPersist.getPedidoById(pedidoId)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/pedidos/" + pedidoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(pedidoPersist, times(1)).getPedidoById(pedidoId);
    }

    @ParameterizedTest
    @MethodSource("errosHttpStatusAndException")
    void testaCreatePedidoComErrosEsperados(HttpStatus httpStatus, Exception exception, String mensagemDeErroEsperada) throws Exception {
        ProcessarPedidoRequest pedidoRequest = ObjectBuilder.buildProcessarPedidoRequestSucesso();

        doThrow(exception).when(pedidoPersist).createPedido(any());

        String actualErrorMessage = Objects.requireNonNull(mockMvc.perform(post("/pedidos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(pedidoRequest)))
                        .andExpect(status().is(httpStatus.value()))
                        .andReturn()
                        .getResolvedException())
                .getMessage();

        assertTrue(actualErrorMessage.contains(mensagemDeErroEsperada));
    }

    private static Stream<Arguments> errosHttpStatusAndException() {
        return Stream.of(
                Arguments.of(HttpStatus.UNAUTHORIZED, new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"), "Unauthorized"),
                Arguments.of(HttpStatus.FORBIDDEN, new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden"), "Forbidden"),
                Arguments.of(HttpStatus.NOT_FOUND, new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"), "Not Found"),
                Arguments.of(HttpStatus.REQUEST_TIMEOUT, new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Request Timeout"), "Request Timeout"),
                Arguments.of(HttpStatus.UNPROCESSABLE_ENTITY, new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity"), "Unprocessable Entity")
        );
    }
}