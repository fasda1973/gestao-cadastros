package gestao_cadastros.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gestao_cadastros.model.Cliente;
import gestao_cadastros.service.ClienteService;

@RestController
public class HelloController {

    private final ClienteService clienteService;

    public HelloController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/cliente")
    public Cliente cliente() {
        return clienteService.buscarCliente();
    }
}