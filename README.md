## TODO: Use Case
- Fluxo de cardapio - Adicionar e atualizar cardapio
- Fluxo de categoria - Criar Categoria e adicionar a cardapio
- Fluxo de prato - Criar prato e adicionar a categoria
- Fluxo do pedido - Usuario do restaurante X criar um pedido com N quantidade de M itens 
- Adicionar e atualizar dados do restaurante (contato e endereco)
- Adicionar e atualizar dados do usuario (contato e endereco)


# 🌟 Padrão de Desenvolvimento dos Endpoints

Este projeto segue um padrão estruturado para garantir organização, reutilização de código e clareza na implementação dos endpoints. Abaixo, explicamos a estrutura e a responsabilidade de cada camada.

## 📌 **1. Controller: Validação e Respostas HTTP**

O **Controller** é responsável por receber as requisições HTTP, validar os dados e retornar a resposta apropriada.

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


✅ **O que ele faz?**
- Valida os dados da requisição, incluindo a verificação de valores `Optional`. Se algum `Optional` estiver vazio, retorna o código de erro HTTP correspondente (por exemplo, 404 para recurso não encontrado ou 400 para dados inválidos).
- Se necessário, retorna códigos de erro HTTP quando os dados não são válidos ou quando a lógica de negócio não é satisfeita.
- Chama o **UseCase** para processar a lógica de negócio.
- Retorna a resposta com o código HTTP adequado, com base no resultado da execução do **UseCase**.

---

## ⚙️ **2. Use Case: Regras de Negócio e Autorização**

O **UseCase** contém a lógica de negócio da aplicação. Ele é responsável por coordenar os processos e chamar os serviços necessários.

✅ **O que ele faz?**
- Obtém o usuário autenticado através do **AuthorizationService**.
- Valida se os recursos pertencem ao usuário antes de modificá-los.
- Chama o **Persist** para buscar, atualizar ou criar registros no banco.

---

## 🗄 **3. Persist: Interação com o Banco de Dados**

A camada de **Persistência** é responsável por manipular os dados antes de salvá-los no banco de dados.

✅ **O que ela faz?**
- Garante que os objetos tenham os atributos essenciais antes de serem armazenados (exemplo: ID e timestamps).
- Aplica regras antes de inserir ou atualizar os registros.
- Utiliza o repositório para interagir com o banco de dados.

---

## 🔗 **Resumo do Fluxo de Chamadas**

1️⃣ **Controller** recebe a requisição, valida os dados e chama o **UseCase**.  
2️⃣ **UseCase** aplica as regras de negócio e chama a camada de **Persistência**.  
3️⃣ **Persistência** trata a manipulação do banco de dados e retorna o resultado.  
4️⃣ **UseCase** converte os dados e devolve para o **Controller**.  
5️⃣ **Controller** retorna a resposta com o código HTTP adequado.

---

Esse padrão garante um fluxo bem estruturado e facilita a manutenção da aplicação no longo prazo! 🚀
