package gestao_cadastros.service;

import org.springframework.stereotype.Service;

import gestao_cadastros.model.Cliente;

@Service
public class ClienteService {

    public Cliente buscarCliente() {
        return new Cliente(1L, "Flavio");
    }
}