package gestao_cadastros.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import gestao_cadastros.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	Page<Produto> findByDescricaoContaining(String texto, Pageable pageable);
}