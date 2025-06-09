package com.projeto.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.inventario.entities.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository <Movimentacao, Long>{

}
