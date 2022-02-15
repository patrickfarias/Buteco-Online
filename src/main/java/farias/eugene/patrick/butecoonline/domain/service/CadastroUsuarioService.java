package farias.eugene.patrick.butecoonline.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import farias.eugene.patrick.butecoonline.domain.exception.UsuarioNaoEncontradoException;
import farias.eugene.patrick.butecoonline.domain.model.Usuario;
import farias.eugene.patrick.butecoonline.domain.repository.EstoqueRepository;
import farias.eugene.patrick.butecoonline.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Transactional
	public Usuario salvar(Usuario usuarioId) {
		return usuarioRepository.save(usuarioId);
	}

	public Usuario buscarOuFalhar(Long usuarioId) {

		Usuario user = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));

		return user;

	}

}
