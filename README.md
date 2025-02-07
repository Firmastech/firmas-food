## TODO: Use Case
- Fluxo de cardapio - Adicionar e atualizar cardapio
- Fluxo de categoria - Criar Categoria e adicionar a cardapio
- Fluxo de prato - Criar prato e adicionar a categoria
- Fluxo do pedido - Usuario do restaurante X criar um pedido com N quantidade de M itens 
- Adicionar e atualizar dados do restaurante (contato e endereco)
- Adicionar e atualizar dados do usuario (contato e endereco)


## Guias de Endpoints:

- Não passar como parâmetro o `RestauranteId` nem `UsuarioId` (já estão no JWT, não é seguro)
- As entidades devem ser utilizadas com nomes no plural, ou seja:
    - `restaurantes`
    - `usuarios`
    - `pedidos`

- Dados no devem ser deletados, i.e os endpoints de `Delete` devem atribuir o valor `false` no campo `ativo` na Entity e adicionar um `LocalDateTime.now()` no campo deletado.
 - Use o prefixo `/rest` para todos endpoints, utilizando os padroes REST
  - Criar prato `POST /rest/pratos`.
  - Alterar prato `PUT /rest/pratos`.
  - Desativar prato `DELETE /rest/pratos/{pratoId}`.
  - Buscar pratos `GET /rest/pratos`.
  - Buscar detalhes prato `GET /rest/pratos/{id}`.
