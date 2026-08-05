package gestao_cadastros.controller;

import gestao_cadastros.dto.ProdutoRequest;
import gestao_cadastros.dto.ProdutoResponse;
import gestao_cadastros.service.ProdutoService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	private final ProdutoService produtoService;
	
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@PostMapping
	public ResponseEntity<ProdutoResponse> salvar(@Valid @RequestBody ProdutoRequest request) {
		
		ProdutoResponse response = produtoService.salvar(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public Page<ProdutoResponse> listarTodos(
			@RequestParam(required = false) String descricao, 
			Pageable pageable){
		
		return produtoService.listarTodos(descricao, pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Long id) {
		
		ProdutoResponse response = produtoService.buscarPorId(id);
		
		return ResponseEntity.ok(response); 
	}
	
	/*
	@PutMapping("/{id}")
	public ProdutoResponse atualizar(
			@PathVariable Long id,
			@Valid @RequestBody ProdutoRequest request) {
		
		return produtoService.atualizar(id, request);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		
		produtoService.excluir(id);
	}
	*/
}