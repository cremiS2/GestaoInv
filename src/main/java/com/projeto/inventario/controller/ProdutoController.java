package com.projeto.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.inventario.dto.ProdutoDTO;
import com.projeto.inventario.entities.Produto;
import com.projeto.inventario.repositories.ProdutoRepository;
import com.projeto.inventario.service.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
@CrossOrigin(origins = "localhost:3000")
public class ProdutoController {

	@Autowired
    ProdutoService produtoService;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@PostMapping(value = "/cadastrar")
    public ResponseEntity<ProdutoDTO> salvarProduto(@RequestBody ProdutoDTO dto) {
        dto = produtoService.salvarProduto(dto);
        return ResponseEntity.ok(dto);
    }
	
	@GetMapping(value = "/todos")
    public List<ProdutoDTO> findAll() {
        return produtoService.findAll();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable Long id) {
		return produtoRepository.findById(id)
	            .map(produto -> ResponseEntity.ok(new ProdutoDTO(produto)))
	            .orElse(ResponseEntity.notFound().build());
	    }
	
	@DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<String> deletarItem(@PathVariable Long id) {
		produtoService.deletarProduto(id);
        return ResponseEntity.ok("Produto deletado");
    }
	
	@PutMapping(value = "/atualizar/{id}")
    public ResponseEntity<Produto> atualizarItem(@PathVariable Long id, @RequestBody Produto atualizado) {
        Produto produto = produtoService.atualizarProduto(id, atualizado);
        return ResponseEntity.ok(produto);
    }
}
