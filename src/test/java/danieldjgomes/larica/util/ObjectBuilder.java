package danieldjgomes.larica.util;

import danieldjgomes.larica.app.usecase.pedido.request.PratosRequestList;
import danieldjgomes.larica.app.usecase.pedido.request.ProcessarPedidoRequest;

import java.util.List;
import java.util.UUID;

public class ObjectBuilder {

    public static ProcessarPedidoRequest buildProcessarPedidoRequestSucesso(){
        return ProcessarPedidoRequest.builder()
                .usuarioId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .id(UUID.fromString("123e4567-e89b-12d3-a456-426614175000"))
                .itensList(buildPratosRequestListSucesso())
                .build();
    }

    public static List<PratosRequestList> buildPratosRequestListSucesso(){
        return List.of(PratosRequestList.builder()
                .pratoId(UUID.fromString("123e4567-e89b-12d3-a456-426614179000"))
                .descricao("descricao")
                .quantidade(1)
                .build());
    }
}
