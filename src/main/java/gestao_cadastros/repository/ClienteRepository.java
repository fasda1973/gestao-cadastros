package gestao_cadastros.repository;

import gestao_cadastros.entity.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Page<Cliente> findByNomeContaining(String texto, Pageable pageable);

}