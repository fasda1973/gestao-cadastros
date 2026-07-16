package gestao_cadastros.controller;

import gestao_cadastros.entity.Cliente;
import gestao_cadastros.service.ClienteService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @PostMapping
    public Cliente salvar(@Valid @RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }
    
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
    	
    	Cliente cliente = clienteService.buscarPorId(id);

        return ResponseEntity.ok(cliente);
    	   	
    }
    
    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.atualizar(id, cliente);
    }
    
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
    	clienteService.excluir(id);
    }

}