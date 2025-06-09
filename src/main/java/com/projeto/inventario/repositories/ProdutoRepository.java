package com.projeto.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.inventario.entities.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long>{

}
