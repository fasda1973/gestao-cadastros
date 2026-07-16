package gestao_cadastros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gestao_cadastros.entity.Cliente;
import gestao_cadastros.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
    
    public Cliente buscarPorId(Long id) {
    	Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return cliente.get();
        }

        throw new RuntimeException("Cliente não encontrado");
    }
    
    public Cliente atualizar(Long id, Cliente cliente) {
    	
    	Optional<Cliente> clienteExistente = clienteRepository.findById(id);
    	
    	if (clienteExistente.isEmpty()) {
   	
    		throw new RuntimeException("Cliente não encontrado");
    	}
    	
    	Cliente clienteSalvo = clienteExistente.get();
    	
    	clienteSalvo.setNome(cliente.getNome());
    	clienteSalvo.setCpf(cliente.getCpf());
    	clienteSalvo.setEmail(cliente.getEmail());
    	clienteSalvo.setTelefone(cliente.getTelefone());
    	
    	return clienteRepository.save(clienteSalvo);
    }
    
    public void excluir(Long id) {
    	clienteRepository.deleteById(id);
    }
}