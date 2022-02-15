package farias.eugene.patrick.butecoonline.domain.exception;

public class EstoqueNaoDisponivelException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstoqueNaoDisponivelException(String mensagem) {
		super(mensagem);
	}

	public EstoqueNaoDisponivelException(Long estoqueId) {
		this(String.format("Não existe um cadastro de Estoque com código %d.", estoqueId));
	}

}
