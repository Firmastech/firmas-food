package danieldjgomes.larica.core.restaurante.entity;

import danieldjgomes.larica.core.cardapio.entity.Cardapio;
import danieldjgomes.larica.core.endereco.entity.Endereco;

import java.util.UUID;


public class Restaurante {

    private UUID id;
    private String nome;
    private Integer tempoEstimadoDeEntrega;

    private StatusFuncionamento statusFuncionamento;
    private Endereco endereco;
    private Cardapio cardapio;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTempoEstimadoDeEntrega() {
        return tempoEstimadoDeEntrega;
    }

    public void setTempoEstimadoDeEntrega(Integer tempoEstimadoDeEntrega) {
        this.tempoEstimadoDeEntrega = tempoEstimadoDeEntrega;
    }

    public StatusFuncionamento getStatusFuncionamento() {
        return statusFuncionamento;
    }

    public void setStatusFuncionamento(StatusFuncionamento statusFuncionamento) {
        this.statusFuncionamento = statusFuncionamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    private enum StatusFuncionamento {
        ABERTO, FECHADO, INATIVO
    }

}
