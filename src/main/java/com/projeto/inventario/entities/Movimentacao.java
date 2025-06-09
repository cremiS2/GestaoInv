package com.projeto.inventario.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_movimentacoes")
public class Movimentacao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    private int quantidade;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "deposito_origem_id")
    private Deposito depositoOrigem;

    @ManyToOne
    @JoinColumn(name = "deposito_destino_id")
    private Deposito depositoDestino;

    public Movimentacao() {
        this.dataHora = LocalDateTime.now();
    }

    public Movimentacao(TipoMovimentacao tipo, int quantidade, Produto produto,
                        Deposito depositoOrigem, Deposito depositoDestino) {
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.produto = produto;
        this.depositoOrigem = depositoOrigem;
        this.depositoDestino = depositoDestino;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setId(Long id) {
		this.id = id;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Deposito getDepositoOrigem() {
        return depositoOrigem;
    }

    public void setDepositoOrigem(Deposito depositoOrigem) {
        this.depositoOrigem = depositoOrigem;
    }

    public Deposito getDepositoDestino() {
        return depositoDestino;
    }

    public void setDepositoDestino(Deposito depositoDestino) {
        this.depositoDestino = depositoDestino;
    }
}