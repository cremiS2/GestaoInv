package com.projeto.inventario.dto;

import java.time.LocalDateTime;

import com.projeto.inventario.entities.Movimentacao;
import com.projeto.inventario.entities.TipoMovimentacao;

public class MovimentacaoDTO {
	 private Long id;
	 private LocalDateTime dataHora;
	 private TipoMovimentacao tipo;
	 private int quantidade;
	 private Long produtoId;
	 private Long depositoOrigemId;
	 private Long depositoDestinoId;
	 
	 public MovimentacaoDTO() {
	 }

	 public MovimentacaoDTO(Long id, LocalDateTime dataHora, TipoMovimentacao tipo, int quantidade, Long produtoId,
			Long depositoOrigemId, Long depositoDestinoId) {
		this.id = id;
		this.dataHora = dataHora;
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.produtoId = produtoId;
		this.depositoOrigemId = depositoOrigemId;
		this.depositoDestinoId = depositoDestinoId;
	}
	 
	 public MovimentacaoDTO(Movimentacao entity) {
		 this.id = entity.getId();
		 this.dataHora = entity.getDataHora();
		 this.tipo = entity.getTipo();
		 this.quantidade = entity.getQuantidade();
		 this.produtoId = entity.getProduto().getId();
		 this.depositoOrigemId = entity.getDepositoOrigem() != null ? entity.getDepositoOrigem().getId() : null;
	     this.depositoDestinoId = entity.getDepositoDestino() != null ? entity.getDepositoDestino().getId() : null;
	 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
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

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Long getDepositoOrigemId() {
		return depositoOrigemId;
	}

	public void setDepositoOrigemId(Long depositoOrigemId) {
		this.depositoOrigemId = depositoOrigemId;
	}

	public Long getDepositoDestinoId() {
		return depositoDestinoId;
	}

	public void setDepositoDestinoId(Long depositoDestinoId) {
		this.depositoDestinoId = depositoDestinoId;
	}
}