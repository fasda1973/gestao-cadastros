package gestao_cadastros.repository;

import gestao_cadastros.entity.Cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNomeContaining(String texto);

}