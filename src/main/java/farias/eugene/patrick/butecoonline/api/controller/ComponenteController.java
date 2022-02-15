package farias.eugene.patrick.butecoonline.api.controller;

import java.util.List;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import farias.eugene.patrick.butecoonline.domain.exception.ComponenteNaoEncontradoException;
import farias.eugene.patrick.butecoonline.domain.exception.NegocioException;
import farias.eugene.patrick.butecoonline.domain.model.Componente;
import farias.eugene.patrick.butecoonline.domain.repository.ComponenteRepository;
import farias.eugene.patrick.butecoonline.domain.service.CadastroComponenteService;

@RestController
@RequestMapping(path = "/componentes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComponenteController {

	@Autowired
	private ComponenteRepository componenteRepository;

	@Autowired
	private CadastroComponenteService cadastroComponenteService;

	@GetMapping
	public List<Componente> listar() {
		return componenteRepository.findAll();

	}

	@GetMapping("/{componenteId}")
	public Componente buscar(@PathVariable Long componenteId) {
		return cadastroComponenteService.buscarOuFalhar(componenteId);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Componente adicionar(@RequestBody @Valid Componente componente) {
		try {

			Componente comp = cadastroComponenteService.salvar(componente);

			comp.calcularPrecoTotal();

			return comp;

		} catch (ComponenteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{componenteId}")
	public Componente atualizar(@PathVariable Long componenteId, @RequestBody @Valid Componente componente) {
		try {

			Componente comp = cadastroComponenteService.buscarOuFalhar(componenteId);

			return cadastroComponenteService.salvar(comp);

		} catch (ComponenteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroComponenteService.excluir(cidadeId);
	}

}
