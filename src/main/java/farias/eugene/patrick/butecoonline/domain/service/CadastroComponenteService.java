package farias.eugene.patrick.butecoonline.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import farias.eugene.patrick.butecoonline.domain.exception.ComponenteNaoEncontradoException;
import farias.eugene.patrick.butecoonline.domain.exception.ProdutoNaoEncontradoException;
import farias.eugene.patrick.butecoonline.domain.model.Componente;
import farias.eugene.patrick.butecoonline.domain.repository.ComponenteRepository;

@Service
public class CadastroComponenteService {

	@Autowired
	private ComponenteRepository componenteRepository;

	@Transactional
	public Componente salvar(Componente componente) {
		return componenteRepository.save(componente);
	}

	@Transactional
	public void excluir(Long componenteId) {
		try {
			componenteRepository.deleteById(componenteId);
			componenteRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(componenteId);

		}
	}

	public Componente buscarOuFalhar(Long componenteId) {
		return componenteRepository.findById(componenteId)
				.orElseThrow(() -> new ComponenteNaoEncontradoException(componenteId));
	}

}
