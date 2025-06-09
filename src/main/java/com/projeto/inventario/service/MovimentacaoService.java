package com.projeto.inventario.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.inventario.dto.MovimentacaoDTO;
import com.projeto.inventario.entities.Deposito;
import com.projeto.inventario.entities.Estoque;
import com.projeto.inventario.entities.Movimentacao;
import com.projeto.inventario.entities.Produto;
import com.projeto.inventario.entities.TipoMovimentacao;
import com.projeto.inventario.repositories.DepositoRepository;
import com.projeto.inventario.repositories.EstoqueRepository;
import com.projeto.inventario.repositories.MovimentacaoRepository;
import com.projeto.inventario.repositories.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private DepositoRepository depositoRepository;

    @Transactional
    public Movimentacao registrarMovimentacao(MovimentacaoDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Deposito origem = dto.getDepositoOrigemId() != null
            ? depositoRepository.findById(dto.getDepositoOrigemId())
                .orElseThrow(() -> new RuntimeException("Depósito de origem não encontrado"))
            : null;

        Deposito destino = dto.getDepositoDestinoId() != null
            ? depositoRepository.findById(dto.getDepositoDestinoId())
                .orElseThrow(() -> new RuntimeException("Depósito de destino não encontrado"))
            : null;

        int quantidade = dto.getQuantidade();
        TipoMovimentacao tipo = dto.getTipo();

        switch (tipo) {
            case ENTRADA -> {
                Estoque estoque = estoqueRepository.findByProdutoAndDeposito(produto, destino);
                if (estoque == null) {
                    estoque = new Estoque(produto, destino, 0);
                }
                estoque.setQuantidade(estoque.getQuantidade() + quantidade);
                estoqueRepository.save(estoque);
            }

            case SAIDA -> {
                Estoque estoque = estoqueRepository.findByProdutoAndDeposito(produto, origem);
                if (estoque != null && estoque.getQuantidade() >= quantidade) {
                    estoque.setQuantidade(estoque.getQuantidade() - quantidade);
                    estoqueRepository.save(estoque);
                } else {
                    throw new RuntimeException("Estoque insuficiente para saída.");
                }
            }

            case TRANSFERENCIA -> {
                Estoque estoqueOrigem = estoqueRepository.findByProdutoAndDeposito(produto, origem);
                if (estoqueOrigem == null || estoqueOrigem.getQuantidade() < quantidade) {
                    throw new RuntimeException("Estoque insuficiente para transferência.");
                }

                estoqueOrigem.setQuantidade(estoqueOrigem.getQuantidade() - quantidade);
                estoqueRepository.save(estoqueOrigem);

                Estoque estoqueDestino = estoqueRepository.findByProdutoAndDeposito(produto, destino);
                if (estoqueDestino == null) {
                    estoqueDestino = new Estoque(produto, destino, 0);
                }

                estoqueDestino.setQuantidade(estoqueDestino.getQuantidade() + quantidade);
                estoqueRepository.save(estoqueDestino);
            }
        }

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setProduto(produto);
        movimentacao.setDepositoOrigem(origem);
        movimentacao.setDepositoDestino(destino);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setTipo(tipo);
        movimentacao.setDataHora(dto.getDataHora() != null ? dto.getDataHora() : LocalDateTime.now());

        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> listarMovimentacoes() {
        return movimentacaoRepository.findAll();
    }
}
