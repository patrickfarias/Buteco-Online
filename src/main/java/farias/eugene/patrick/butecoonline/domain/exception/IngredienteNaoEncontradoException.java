package farias.eugene.patrick.butecoonline.domain.exception;

public class IngredienteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public IngredienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public IngredienteNaoEncontradoException(Long ingredienteId) {
		this(String.format("Não existe um cadastro de componente com código %d.", ingredienteId));
	}
		
}
