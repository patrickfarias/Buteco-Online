package farias.eugene.patrick.butecoonline.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import farias.eugene.patrick.butecoonline.domain.exception.ProdutoNaoEncontradoException;
import farias.eugene.patrick.butecoonline.domain.model.Produto;
import farias.eugene.patrick.butecoonline.domain.repository.EstoqueRepository;
import farias.eugene.patrick.butecoonline.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Transactional
	public Produto salvar(Produto produtoId) {
		return produtoRepository.save(produtoId);
	}

	public Produto buscarOuFalhar(Long produtoId) {

		Produto prod = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));

		prod.calcularValorTotal();

		return prod;

	}
	
	@Transactional
	public void excluir(Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);
			produtoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(produtoId);
		
		} 
	}

}
