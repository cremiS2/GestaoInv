package com.projeto.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.inventario.entities.Deposito;

@Repository
public interface DepositoRepository extends JpaRepository <Deposito, Long>{

}
