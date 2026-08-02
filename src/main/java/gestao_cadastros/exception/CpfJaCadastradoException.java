package gestao_cadastros.exception;

public class CpfJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public CpfJaCadastradoException(String mensagem) {
        super(mensagem);
    }

}