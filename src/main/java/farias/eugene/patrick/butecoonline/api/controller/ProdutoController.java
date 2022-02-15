package farias.eugene.patrick.butecoonline.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import farias.eugene.patrick.butecoonline.domain.exception.NegocioException;
import farias.eugene.patrick.butecoonline.domain.exception.ProdutoNaoEncontradoException;
import farias.eugene.patrick.butecoonline.domain.model.Produto;
import farias.eugene.patrick.butecoonline.domain.repository.ProdutoRepository;
import farias.eugene.patrick.butecoonline.domain.service.CadastroProdutoService;

@RestController
@RequestMapping(path = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@GetMapping
	public List<Produto> listar() {

		return produtoRepository.findAll();

	}

	@GetMapping("/{produtoId}")
	public Produto buscar(@PathVariable Long produtoId) {

		Produto prod = cadastroProdutoService.buscarOuFalhar(produtoId);

		prod.calcularValorTotal();

		return cadastroProdutoService.buscarOuFalhar(produtoId);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionar(@RequestBody @Valid Produto produto) {
		try {

			Produto prod = cadastroProdutoService.salvar(produto);

			prod.calcularValorTotal();

			return prod;

		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{produtoId}")
	public Produto atualizar(@PathVariable Long produtoId, @RequestBody @Valid Produto produto) {
		try {

			produto.calcularValorTotal();

			return cadastroProdutoService.salvar(produto);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long produtoId) {
		cadastroProdutoService.excluir(produtoId);
	}

}
