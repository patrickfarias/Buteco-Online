package farias.eugene.patrick.butecoonline.api.controller;

import java.util.List;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import farias.eugene.patrick.butecoonline.domain.exception.IngredienteNaoEncontradoException;
import farias.eugene.patrick.butecoonline.domain.exception.NegocioException;
import farias.eugene.patrick.butecoonline.domain.model.Ingrediente;
import farias.eugene.patrick.butecoonline.domain.repository.IngredienteRepository;
import farias.eugene.patrick.butecoonline.domain.service.CadastroIngredienteService;

@RestController
@RequestMapping(path = "/ingredientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredienteController {

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Autowired
	private CadastroIngredienteService cadastroIngredienteService;

	@GetMapping
	public List<Ingrediente> listar() {
		return ingredienteRepository.findAll();

	}

	@GetMapping("/{ingredienteId}")
	public Ingrediente buscar(@PathVariable Long ingredienteId) {
		return cadastroIngredienteService.buscarOuFalhar(ingredienteId);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ingrediente adicionar(@RequestBody @Valid Ingrediente ingrediente) {
		try {

			Ingrediente ingred = cadastroIngredienteService.salvar(ingrediente);

			return ingred;

		} catch (IngredienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{ingredienteId}")
	public Ingrediente atualizar(@PathVariable Long ingredienteId, @RequestBody @Valid Ingrediente ingrediente) {
		try {

			Ingrediente ingred = cadastroIngredienteService.buscarOuFalhar(ingredienteId);

			return cadastroIngredienteService.salvar(ingred);

		} catch (IngredienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
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
