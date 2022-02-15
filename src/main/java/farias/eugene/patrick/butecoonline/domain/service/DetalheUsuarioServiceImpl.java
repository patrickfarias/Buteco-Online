package farias.eugene.patrick.butecoonline.domain.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import farias.eugene.patrick.butecoonline.data.DetalheUsuarioData;
import farias.eugene.patrick.butecoonline.domain.model.Usuario;
import farias.eugene.patrick.butecoonline.domain.repository.UsuarioRepository;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

	private final UsuarioRepository repository;

	public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByLogin(username);
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
		}

		return new DetalheUsuarioData(usuario);
	}

}