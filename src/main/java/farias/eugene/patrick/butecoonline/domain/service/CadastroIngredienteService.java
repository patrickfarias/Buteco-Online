package farias.eugene.patrick.butecoonline.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import farias.eugene.patrick.butecoonline.domain.exception.IngredienteNaoEncontradoException;
import farias.eugene.patrick.butecoonline.domain.model.Ingrediente;
import farias.eugene.patrick.butecoonline.domain.repository.IngredienteRepository;

@Service
public class CadastroIngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Transactional
	public Ingrediente salvar(Ingrediente ingredienteId) {
		return ingredienteRepository.save(ingredienteId);
	}

	public Ingrediente buscarOuFalhar(Long ingredienteId) {
		return ingredienteRepository.findById(ingredienteId)
				.orElseThrow(() -> new IngredienteNaoEncontradoException(ingredienteId));
	}

	@Transactional
	public void excluir(Long ingredienteId) {
		try {
			ingredienteRepository.deleteById(ingredienteId);
			ingredienteRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new IngredienteNaoEncontradoException(ingredienteId);

		}
	}

}
