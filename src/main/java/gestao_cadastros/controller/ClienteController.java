package gestao_cadastros.controller;

import gestao_cadastros.dto.ClienteRequest;
import gestao_cadastros.dto.ClienteResponse;
import gestao_cadastros.service.ClienteService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @PostMapping
    public ClienteResponse salvar(@Valid @RequestBody ClienteRequest request) {
        return clienteService.salvar(request);
    }
       
    @GetMapping
    public Page<ClienteResponse> listarTodos(
    		@RequestParam(required = false) String nome,
    		Pageable pageable) {
        return clienteService.listarTodos(nome, pageable);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable Long id) {
    	
    	ClienteResponse response = clienteService.buscarPorId(id);

        return ResponseEntity.ok(response);
    	   	
    }
    
    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        return clienteService.atualizar(id, request);
    }
    
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
    	clienteService.excluir(id);
    }

}