package gestao_cadastros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import gestao_cadastros.dto.ClienteRequest;
import gestao_cadastros.dto.ClienteResponse;
import gestao_cadastros.entity.Cliente;
import gestao_cadastros.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    public ClienteResponse salvar(ClienteRequest request) {
    	
    	Cliente cliente = new Cliente();
    	
    	cliente.setNome(request.getNome());
    	cliente.setCpf(request.getCpf());
    	cliente.setEmail(request.getEmail());
    	cliente.setTelefone(request.getTelefone());
    
        cliente = clienteRepository.save(cliente);
        
        return converterParaResponse(cliente);
    }
    
    public List<ClienteResponse> listarTodos(String nome) {
    	   	   	    
    	List<Cliente> clientes;
    	
    	if (!StringUtils.hasText(nome)) {
    		clientes = clienteRepository.findAll();
    	} else {
    		clientes = clienteRepository.findByNomeContaining(nome);
    	}
    	
    	List<ClienteResponse> responses = new ArrayList<>();
    	
    	for (Cliente cliente : clientes) {
	        
	        responses.add(converterParaResponse(cliente));
        
    	}
        
        return responses;
    }
    
    public ClienteResponse buscarPorId(Long id) {
    	Optional<Cliente> clienteExistente = clienteRepository.findById(id);
    	
    	if (clienteExistente.isEmpty()) {
    	   	
    		throw new RuntimeException("Cliente não encontrado");
    	}
    	
    	Cliente clienteRetorna = clienteExistente.get();
           
        return converterParaResponse(clienteRetorna);
       
    }
    
    public ClienteResponse atualizar(Long id, ClienteRequest request) {
    	
    	Optional<Cliente> clienteExistente = clienteRepository.findById(id);
    	
    	if (clienteExistente.isEmpty()) {
   	
    		throw new RuntimeException("Cliente não encontrado");
    	}
    	
    	Cliente clienteSalvo = clienteExistente.get();
    	
    	clienteSalvo.setNome(request.getNome());
    	clienteSalvo.setCpf(request.getCpf());
    	clienteSalvo.setEmail(request.getEmail());
    	clienteSalvo.setTelefone(request.getTelefone());
    	
    	clienteSalvo = clienteRepository.save(clienteSalvo);
    	
    	return converterParaResponse(clienteSalvo);
    }
    
    public void excluir(Long id) {
    	clienteRepository.deleteById(id);
    }
    
    private ClienteResponse converterParaResponse(Cliente cliente) {

        ClienteResponse response = new ClienteResponse();

        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        response.setEmail(cliente.getEmail());
        response.setTelefone(cliente.getTelefone());

        return response;
    }
}