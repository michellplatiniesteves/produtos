package br.com.produtos.security;

import br.com.produtos.entidade.Usuario;
import br.com.produtos.entidade.UsuarioDetailsImpl;
import br.com.produtos.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario =usuarioRepository.findByEmail(email).orElseThrow( () ->new UsernameNotFoundException("Usuario não localoizado"));
        return new UsuarioDetailsImpl(usuario);
    }
}
