package farias.eugene.patrick.butecoonline.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import farias.eugene.patrick.butecoonline.domain.exception.NegocioException;
import farias.eugene.patrick.butecoonline.domain.exception.UsuarioNaoEncontradoException;
import farias.eugene.patrick.butecoonline.domain.model.Usuario;
import farias.eugene.patrick.butecoonline.domain.repository.UsuarioRepository;
import farias.eugene.patrick.butecoonline.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {

		return ResponseEntity.ok(usuarioRepository.findAll());

	}

	// Login repedito nao sera permitido.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> adicionar(@RequestBody @Valid Usuario usuario) {
		try {

			usuario.setSenha(encoder.encode(usuario.getSenha()));
			return ResponseEntity.ok(usuarioRepository.save(usuario));

		} catch (UsuarioNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@GetMapping("/validarSenha")
	public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String senha) {

		Optional<Usuario> opUsuario = usuarioRepository.findByLogin(login);

		if (opUsuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}

		Usuario usuario = opUsuario.get();

		boolean valid = encoder.matches(senha, usuario.getSenha());

		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

		return ResponseEntity.status(status).body(valid);

	}

	@GetMapping("/{usuarioId}")
	public Usuario buscar(@PathVariable Long usuarioId) {

		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

		return usuario;
	}

	@PutMapping("/{usuarioId}")
	public Usuario atualizar(@PathVariable Long usuarioId, @RequestBody @Valid Usuario usuario) {
		try {
			Usuario user = cadastroUsuarioService.buscarOuFalhar(usuarioId);

			user.setLogin(usuario.getLogin());
			user.setSenha(encoder.encode(usuario.getSenha()));

			return cadastroUsuarioService.salvar(user);

		} catch (UsuarioNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Transactional
	public void excluir(Long usuarioId) {
		try {
			usuarioRepository.deleteById(usuarioId);
			usuarioRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(usuarioId);

		}
	}

}
