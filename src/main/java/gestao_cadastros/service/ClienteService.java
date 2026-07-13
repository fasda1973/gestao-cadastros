package gestao_cadastros.service;

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
}