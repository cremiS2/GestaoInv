package com.projeto.inventario.dto;

import com.projeto.inventario.entities.Produto;

public class ProdutoDTO {
	private Long id;
	private String descricao;
	private String nome;
	private float preco;
	private String imgUrl;
	
	public ProdutoDTO() {
	}

	public ProdutoDTO(Long id, String descricao, String nome, float preco, String imgUrl) {
		this.id = id;
		this.descricao = descricao;
		this.nome = nome;
		this.preco = preco;
		this.imgUrl = imgUrl;
	}
	
	public ProdutoDTO(Produto entity) {
		this.id = entity.getId();
		this.descricao = entity.getDescricao();
		this.nome = entity.getNome();
		this.preco = entity.getPreco();
		this.imgUrl = entity.getImgUrl();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
