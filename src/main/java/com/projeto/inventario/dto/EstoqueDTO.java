package com.projeto.inventario.dto;

import com.projeto.inventario.entities.Estoque;

public class EstoqueDTO {
    private Long produtoId;
    private String produtoNome;
    private Long depositoId;
    private String depositoNome;
    private int quantidade;

    public EstoqueDTO() {}

    public EstoqueDTO(Long produtoId, Long depositoId, int quantidade) {
        this.produtoId = produtoId;
        this.depositoId = depositoId;
        this.quantidade = quantidade;
    }

    public EstoqueDTO(Estoque entity) {
        this.produtoId = entity.getProduto().getId();
        this.produtoNome = entity.getProduto().getNome();
        this.depositoId = entity.getDeposito().getId();
        this.depositoNome = entity.getDeposito().getNome();
        this.quantidade = entity.getQuantidade();
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public Long getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(Long depositoId) {
        this.depositoId = depositoId;
    }

    public String getDepositoNome() {
        return depositoNome;
    }

    public void setDepositoNome(String depositoNome) {
        this.depositoNome = depositoNome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
