package com.projeto.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.inventario.entities.Deposito;
import com.projeto.inventario.entities.Estoque;
import com.projeto.inventario.entities.Produto;

@Repository
public interface EstoqueRepository extends JpaRepository <Estoque, Long>{

	Estoque findByProdutoAndDeposito(Produto produto, Deposito deposito);

}
