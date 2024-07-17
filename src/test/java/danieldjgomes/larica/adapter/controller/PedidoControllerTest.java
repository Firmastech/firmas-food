package danieldjgomes.larica.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import danieldjgomes.larica.adapter.database.pedidos.impl.PedidoPersistImpl;
import danieldjgomes.larica.app.LaricaApplication;
import danieldjgomes.larica.usecase.pedido.ProcessarPedidoUsecaseImpl;
import danieldjgomes.larica.usecase.pedido.request.ProcessarPedidoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
@ContextConfiguration(classes = LaricaApplication.class)
class PedidoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoPersistImpl pedidoPersistImpl;

    @MockBean
    private ProcessarPedidoUsecaseImpl processarPedidoUsecase;

    @Test
    void shouldCreatePedido() throws Exception {
        ProcessarPedidoRequest pedidoEntity = ProcessarPedidoRequest.builder().build();

        when(pedidoPersistImpl.getPedidoById(any())).thenReturn(ProcessarPedidoRequest.builder().build());

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pedidoEntity)))
                .andExpect(status().isOk());

        verify(pedidoPersistImpl, times(1)).createPedido(any(ProcessarPedidoRequest.class));
    }

    @Test
    void shouldProcessPedido() throws Exception {
        UUID pedidoId = UUID.randomUUID();
        ProcessarPedidoRequest processarPedidoRequest = ProcessarPedidoRequest.builder().build();

        when(pedidoPersistImpl.getPedidoById(pedidoId)).thenReturn(processarPedidoRequest);

        mockMvc.perform(post("/pedidos/" + pedidoId + "/processar"))
                .andExpect(status().isOk());

        verify(processarPedidoUsecase, times(1)).processarPedido(any(ProcessarPedidoRequest.class));
    }

    @Test
    void shouldGetPedido() throws Exception {
        UUID pedidoId = UUID.randomUUID();
        ProcessarPedidoRequest processarPedidoRequest = ProcessarPedidoRequest.builder().build();

        when(pedidoPersistImpl.getPedidoById(pedidoId)).thenReturn(processarPedidoRequest);

        mockMvc.perform(get("/pedidos/" + pedidoId))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(processarPedidoRequest)));

        verify(pedidoPersistImpl, times(1)).getPedidoById(pedidoId);
    }
}