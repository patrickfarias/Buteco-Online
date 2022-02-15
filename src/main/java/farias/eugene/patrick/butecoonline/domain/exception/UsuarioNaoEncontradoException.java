package farias.eugene.patrick.butecoonline.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(Long produtoId) {
		this(String.format("Não existe um cadastro de produto com código %d.", produtoId));
	}
		
}
