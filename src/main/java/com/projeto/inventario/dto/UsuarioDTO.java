package com.projeto.inventario.dto;

import com.projeto.inventario.entities.StatusRole;
import com.projeto.inventario.entities.Usuario;

public class UsuarioDTO {
	 private Long id;
	 private String login;
	 private String senha;
	 private StatusRole role;
	 
	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String login, String senha, StatusRole role) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.role = role;
	}

	public UsuarioDTO(Usuario entity) {
		this.id = entity.getId();
		this.login = entity.getLogin();
		this.senha = entity.getSenha();
		this.role = entity.getRole();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public StatusRole getRole() {
		return role;
	}

	public void setRole(StatusRole role) {
		this.role = role;
	}
}
