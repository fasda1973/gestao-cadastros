package gestao_cadastros.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gestao_cadastros.dto.ErroResponse;

import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> tratarRuntimeException(RuntimeException ex) {
        
		return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> tratarValidacao(MethodArgumentNotValidException ex) {

	    Map<String, String> erros = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(erro -> {
	        erros.put(erro.getField(), erro.getDefaultMessage());
	    });

	    return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> tratarClienteNaoEncontrado(ClienteNaoEncontradoException ex) {
		
		ErroResponse erroResponse = new ErroResponse();
		
		erroResponse.setStatus(HttpStatus.NOT_FOUND.value());
		erroResponse.setMensagem(ex.getMessage());
		erroResponse.setDataHora(LocalDateTime.now());
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(erroResponse);
	}
	
	@ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> tratarProdutoNaoEncontrado(ProdutoNaoEncontradoException ex) {
		
		ErroResponse erroResponse = new ErroResponse();
		
		erroResponse.setStatus(HttpStatus.NOT_FOUND.value());
		erroResponse.setMensagem(ex.getMessage());
		erroResponse.setDataHora(LocalDateTime.now());
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(erroResponse);
	}

}