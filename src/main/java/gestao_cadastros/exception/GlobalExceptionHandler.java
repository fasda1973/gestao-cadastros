package gestao_cadastros.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

}