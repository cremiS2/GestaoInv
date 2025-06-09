package com.projeto.inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projeto.inventario.dto.ProdutoDTO;
import com.projeto.inventario.entities.Produto;
import com.projeto.inventario.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public ProdutoDTO salvarProduto(ProdutoDTO dto) {
		Produto produto = new Produto();
		
		
		produto.setDescricao(dto.getDescricao());
		produto.setPreco(dto.getPreco());
		produto.setImgUrl(dto.getImgUrl());
		produto.setNome(dto.getNome());
		
		produto = produtoRepository.save(produto);

		return new ProdutoDTO(produto);
	}
	
	public List<ProdutoDTO> findAll(){
        List<Produto> lista = produtoRepository.findAll();
        return lista.stream().map(x-> new ProdutoDTO(x)).toList();
    }
	
	public ResponseEntity<Produto> findUsuarioById(Long id){
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	
	 public String deletarProduto(Long id) {
		 	produtoRepository.deleteById(id);
			return "Produto deletado!";
	}
	 
	 public Produto atualizarProduto(Long id, Produto atualizado) {
	    	Produto produto = produtoRepository.findById(id).get(); 
	    	

	    	produto.setDescricao(atualizado.getDescricao());
	    	produto.setNome(atualizado.getNome());
	    	produto.setPreco(atualizado.getPreco());
	    	produto.setImgUrl(atualizado.getImgUrl());
	    	
			return produtoRepository.save(produto);
		}
}
