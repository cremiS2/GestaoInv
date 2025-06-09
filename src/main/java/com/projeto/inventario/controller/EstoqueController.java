package com.projeto.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.inventario.dto.EstoqueDTO;
import com.projeto.inventario.entities.Estoque;
import com.projeto.inventario.service.EstoqueService;

@RestController
@RequestMapping("/estoques")
@CrossOrigin("*")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping(value = "/todos")
    public List<EstoqueDTO> listarTodos() {
        return estoqueService.listarTodos();
    }

    @PostMapping(value = "/criar")
    public Estoque salvar(@RequestBody EstoqueDTO dto) {
        return estoqueService.salvar(dto);
    }

    @PutMapping(value = "/atualizar/{id}/quantidade")
    public Estoque atualizarQuantidade(@PathVariable Long id, @RequestParam int quantidade) {
        return estoqueService.atualizarQuantidade(id, quantidade);
    }

    @DeleteMapping(value = "/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        estoqueService.deletar(id);
        return "Estoque deletado com sucesso!";
    }
}