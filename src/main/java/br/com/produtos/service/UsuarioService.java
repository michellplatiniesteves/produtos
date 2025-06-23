package br.com.produtos.service;

import br.com.produtos.entidade.DTO.CriarUsuarioDto;
import br.com.produtos.entidade.DTO.LoginUsuarioDto;
import br.com.produtos.entidade.DTO.RetornarJwtTokenDto;
import br.com.produtos.entidade.DTO.Roles;
import br.com.produtos.entidade.Role;
import br.com.produtos.entidade.Usuario;
import br.com.produtos.entidade.UsuarioDetailsImpl;
import br.com.produtos.repository.UsuarioRepository;
import br.com.produtos.security.JwtTokenService;
import br.com.produtos.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RetornarJwtTokenDto authenticateUser(LoginUsuarioDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.senha());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RetornarJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    // Método responsável por criar um usuário
    public void CriarUsuario(CriarUsuarioDto CriarUsuarioDto) {

        // Cria um novo usuário com os dados fornecidos
        Usuario newUser = Usuario.builder()
                .nome(CriarUsuarioDto.nome())
                .email(CriarUsuarioDto.email())
                // Codifica a senha do usuário com o algoritmo bcrypt
                .senha(securityConfiguration.passwordEncoder().encode(CriarUsuarioDto.senha()))
                // Atribui ao usuário uma permissão específica
                .roles(List.of(Role.builder().role(CriarUsuarioDto.roles()).build()))
                .build();

        // Salva o novo usuário no banco de dados
        userRepository.save(newUser);
    }
}

