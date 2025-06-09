package com.projeto.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.inventario.dto.DepositoDTO;
import com.projeto.inventario.entities.Deposito;
import com.projeto.inventario.repositories.DepositoRepository;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    public DepositoDTO salvarDeposito(DepositoDTO dto) {
        Deposito deposito = new Deposito();
        deposito.setNome(dto.getNome());
        deposito.setLocalizacao(dto.getLocalizacao());

        deposito = depositoRepository.save(deposito);
        return new DepositoDTO(deposito);
    }

    public List<Deposito> listarTodos() {
        return depositoRepository.findAll();
    }

    public Deposito atualizarDeposito(Long id, Deposito novo) {
        Deposito atual = depositoRepository.findById(id).get();
        atual.setNome(novo.getNome());
        atual.setLocalizacao(novo.getLocalizacao());
        return depositoRepository.save(atual);
    }

    public void deletarDeposito(Long id) {
        depositoRepository.deleteById(id);
    }
}
