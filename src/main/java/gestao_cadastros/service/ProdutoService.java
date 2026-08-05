package gestao_cadastros.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import gestao_cadastros.dto.ProdutoResponse;
import gestao_cadastros.dto.ProdutoRequest;
import gestao_cadastros.entity.Produto;
import gestao_cadastros.exception.ProdutoNaoEncontradoException;
import gestao_cadastros.mapper.ProdutoMapper;
import gestao_cadastros.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private final ProdutoRepository produtoRepository;
	private final ProdutoMapper produtoMapper;
	
	public ProdutoService(ProdutoRepository produtoRepository,
						  ProdutoMapper produtoMapper) {
		this.produtoRepository = produtoRepository;
		this.produtoMapper = produtoMapper;
	}
	
	public ProdutoResponse salvar(ProdutoRequest request) {
		
		Produto produto = produtoMapper.toEntity(request);
		
		produto = produtoRepository.save(produto);		
		
		return produtoMapper.toResponse(produto);
	}
	
	public Page<ProdutoResponse> listarTodos(String descricao, Pageable pageable) {
		
		Page<Produto> produto;
		
		if (!StringUtils.hasText(descricao)) {
			produto = produtoRepository.findAll(pageable);			
		} else {
			produto = produtoRepository.findByDescricaoContaining(descricao, pageable);
		}
		
		return produto.map(produtoMapper::toResponse);
	}
	
	public ProdutoResponse buscarPorId(Long id) {
		
		Produto produto = produtoRepository
				.findById(id)
				.orElseThrow(() -> 
					new ProdutoNaoEncontradoException("Produto não encontrado"));	
		
		return produtoMapper.toResponse(produto);
	}
	
	/*
	public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
		
		Produto produto = produtoRepository
				.findById(id)
				.orElseThrow(() ->
					new ProdutoNaoEncontradoException("Produto não encontrado"));
		
		produto.setDescricao(request.getDescricao());
		produto.setEstoque(request.getEstoque());
		produto.setPreco(request.getPreco());
		
		produto = produtoRepository.save(produto);
		
		return produtoMapper.toResponse(produto);
	}
	
	public void excluir(Long id) {
		produtoRepository.deleteById(id);
	}
	*/	
}