package com.projeto.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.inventario.dto.EstoqueDTO;
import com.projeto.inventario.entities.Deposito;
import com.projeto.inventario.entities.Estoque;
import com.projeto.inventario.entities.Produto;
import com.projeto.inventario.repositories.DepositoRepository;
import com.projeto.inventario.repositories.EstoqueRepository;
import com.projeto.inventario.repositories.ProdutoRepository;

@Service
public class EstoqueService {

	@Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private DepositoRepository depositoRepository;

    public Estoque salvar(EstoqueDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Deposito deposito = depositoRepository.findById(dto.getDepositoId())
                .orElseThrow(() -> new RuntimeException("Depósito não encontrado"));

        Estoque estoque = new Estoque(produto, deposito, dto.getQuantidade());

        return estoqueRepository.save(estoque);
    }

    public List<EstoqueDTO> listarTodos() {
        List<Estoque> lista = estoqueRepository.findAll();
        return lista.stream()
                    .map(EstoqueDTO::new)
                    .toList();
    }


    public Estoque atualizarQuantidade(Long id, int novaQuantidade) {
        Estoque estoque = estoqueRepository.findById(id).get();
        estoque.setQuantidade(novaQuantidade);
        return estoqueRepository.save(estoque);
    }

    public void deletar(Long id) {
        estoqueRepository.deleteById(id);
    }
}

