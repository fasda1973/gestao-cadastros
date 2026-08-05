package gestao_cadastros.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import gestao_cadastros.dto.ClienteRequest;
import gestao_cadastros.dto.ClienteResponse;
import gestao_cadastros.entity.Cliente;
import gestao_cadastros.exception.ClienteNaoEncontradoException;
import gestao_cadastros.mapper.ClienteMapper;
import gestao_cadastros.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository,
    					  ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }
        
    public ClienteResponse salvar(ClienteRequest request) {
    	
    	Cliente cliente = clienteMapper.toEntity(request);
    
        cliente = clienteRepository.save(cliente);
        
        return clienteMapper.toResponse(cliente);
    }
    
    public Page<ClienteResponse> listarTodos(String nome, Pageable pageable) {
    	   	   	    
    	Page<Cliente> clientes;
    	
    	if (!StringUtils.hasText(nome)) {
    		clientes = clienteRepository.findAll(pageable);
    	} else {
    		clientes = clienteRepository.findByNomeContaining(nome, pageable);
    	}
        
        return clientes.map(clienteMapper::toResponse);
    }
    
    public ClienteResponse buscarPorId(Long id) {
    	
    	Cliente cliente = clienteRepository
    			.findById(id)
    			.orElseThrow(() ->
    				new ClienteNaoEncontradoException("Cliente não encontrado"));    	
           
        return clienteMapper.toResponse(cliente);
       
    }
    
    public ClienteResponse atualizar(Long id, ClienteRequest request) {
    	
    	Cliente cliente = clienteRepository
    			.findById(id)
    			.orElseThrow(() -> 
    				new ClienteNaoEncontradoException("Cliente não encontrado"));
    	
    	cliente.setNome(request.getNome());
    	cliente.setCpf(request.getCpf());
    	cliente.setEmail(request.getEmail());
    	cliente.setTelefone(request.getTelefone());
    	
    	cliente = clienteRepository.save(cliente);
    	
    	return clienteMapper.toResponse(cliente);
    }
    
    public void excluir(Long id) {
    	clienteRepository.deleteById(id);
    }
      
}