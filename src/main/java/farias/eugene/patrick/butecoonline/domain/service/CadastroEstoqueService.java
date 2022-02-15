package farias.eugene.patrick.butecoonline.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import farias.eugene.patrick.butecoonline.domain.exception.EstoqueNaoDisponivelException;
import farias.eugene.patrick.butecoonline.domain.model.Estoque;
import farias.eugene.patrick.butecoonline.domain.repository.EstoqueRepository;

@Service
public class CadastroEstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Transactional
	public Estoque salvar(Estoque estoqueId) {
		return estoqueRepository.save(estoqueId);
	}

	public Estoque buscarOuFalhar(Long produtoId) {
		return estoqueRepository.findById(produtoId).orElseThrow(() -> new EstoqueNaoDisponivelException(produtoId));
	}

	@Transactional
	public void excluir(Long estoqueId) {
		try {
			estoqueRepository.deleteById(estoqueId);
			estoqueRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new EstoqueNaoDisponivelException(estoqueId);

		}
	}

}
