package farias.eugene.patrick.butecoonline.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import farias.eugene.patrick.butecoonline.domain.exception.EstoqueNaoDisponivelException;
import farias.eugene.patrick.butecoonline.domain.exception.NegocioException;
import farias.eugene.patrick.butecoonline.domain.model.Estoque;
import farias.eugene.patrick.butecoonline.domain.repository.EstoqueRepository;
import farias.eugene.patrick.butecoonline.domain.service.CadastroEstoqueService;

@RestController
@RequestMapping(path = "/estoque", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstoqueController {

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private CadastroEstoqueService cadastroEstoqueService;

	@GetMapping
	public List<Estoque> listar() {
		return estoqueRepository.findAll();

	}

	@GetMapping("/{estoqueId}")
	public Estoque buscar(@PathVariable Long estoqueId) {
		return cadastroEstoqueService.buscarOuFalhar(estoqueId);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estoque adicionar(@RequestBody @Valid Estoque estoque) {
		try {

			return cadastroEstoqueService.salvar(estoque);

		} catch (EstoqueNaoDisponivelException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{Id}/atualizaestoque")
	public ResponseEntity<?> realizarEntrada(@PathVariable @Valid Long Id, @RequestBody @Valid Estoque estoque) {

		try {

			Estoque estoqueAtual = cadastroEstoqueService.buscarOuFalhar(Id);
			Long id = estoqueAtual.getIngrediente().getId();
			BeanUtils.copyProperties(estoque, estoqueAtual, "id", "ingrediente");
			estoqueRepository.save(estoque);

			return ResponseEntity.ok(estoqueAtual);

		} catch (EstoqueNaoDisponivelException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{estoqueId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estoqueId) {
		cadastroEstoqueService.excluir(estoqueId);
	}

}
