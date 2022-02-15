package farias.eugene.patrick.butecoonline.domain.exception;

public class ComponenteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ComponenteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ComponenteNaoEncontradoException(Long componenteId) {
		this(String.format("Não existe um cadastro de componente com código %d.", componenteId));
	}

}
